package com.ego.casino.service.Impl;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AuthService;
import com.ego.casino.dto.*;
import com.ego.casino.util.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        CustomUserDetails userDetails = new CustomUserDetails(email, userEntity.getPassword());

        if (!jwtTokenUtil.validateToken(tokenEntity.getToken(), userDetails)) {
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
        tokenEntity.setToken(token);

        userService.createUser(userEntity);
        tokenService.saveToken(tokenEntity);
        mailService.sendMail(email, subject, content);
    }

    @Override
    public CustomUserDetails getUserDetailsByEmail(String email) throws UsernameNotFoundException {
        UserEntity user = userService.findByEmail(email);

        return new CustomUserDetails(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public void activate(ActivationRequestDto activationRequestDto) {
        UserEntity user = userService.findByEmail(activationRequestDto.getEmail());

        user.setActivatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userService.createUser(user);
    }


}
