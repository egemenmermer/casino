package com.ego.casino.service.Impl;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AuthService;
import com.ego.casino.dto.*;
import com.ego.casino.util.JwtTokenUtil;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  JwtTokenUtil tokenUtil;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        String password = loginRequestDto.getPassword();
        String email = loginRequestDto.getEmail();
        logger.info(email);
        logger.info(password);

        UserEntity userEntity = userService.findByEmail(email);
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email");
        }

        if (!passwordEncoder.passwordEncoderBean().matches(password, userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        if (userEntity.getActivatedAt() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account is not activated");
        }

        TokenEntity tokenEntity = tokenService.findByUserId(userEntity.getId());
        if (tokenEntity == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token not found for user");
        }

        CustomUserDetails userDetails = new CustomUserDetails(email, userEntity.getPassword());

        if (!tokenUtil.validateToken(tokenEntity.getToken(), userDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return new LoginResponseDto("Login Success!");

    }

    @Override
    @Transactional
    public void register(RegisterRequestDto registerRequestDto, String token) {
        String email = registerRequestDto.getEmail();
        String password = registerRequestDto.getPassword();
        String subject = "Welcome to Bets10";
        String content = "Hello " + registerRequestDto.getEmail() + ",\n\nYour registration was successful. "
                + "Activate your account with this token:\n\n" + token + "\n\nThank you!";

        if (userService.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");
        }

        UserEntity userEntity = new UserEntity();
        TokenEntity tokenEntity = new TokenEntity();

        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.passwordEncoderBean().encode(password));
        userService.createUser(userEntity);

        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        tokenEntity.setExpireDate(tokenUtil.getExpirationDateFromToken(token));
        tokenEntity.setUserId(userEntity.getId());
        tokenService.saveToken(tokenEntity);
        mailService.sendMail(email, subject, content);
    }

    @Override
    public void activate(ActivationRequestDto activationRequestDto) {
        UserEntity user = userService.findByEmail(activationRequestDto.getEmail());
        TokenEntity tokenEntity = tokenService.findByUserId(user.getId());

        user.setActivatedAt(Timestamp.valueOf(LocalDateTime.now()));
        tokenEntity.setActive(true);
        tokenEntity.setUserId(user.getId());

        tokenService.saveToken(tokenEntity);
        userService.createUser(user);
    }
}
