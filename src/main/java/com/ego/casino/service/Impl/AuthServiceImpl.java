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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, String token) {

        String password = loginRequestDto.getPassword();
        String email = loginRequestDto.getEmail();

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

        TokenEntity tokenEntity = tokenService.findByUserId(userEntity);
        if (tokenEntity == null || !tokenUtil.validateToken(token, new CustomUserDetails(email, userEntity.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tokenEntity.setExpireDate(tokenUtil.getExpirationDateFromToken(token));
        tokenService.saveToken(tokenEntity);

        CustomUserDetails userDetails = new CustomUserDetails(email, userEntity.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new LoginResponseDto("Login successful", token);
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
        tokenEntity.setExpireDate(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)));
        tokenEntity.setUserId(userEntity);

        tokenService.saveToken(tokenEntity);
        mailService.sendMail(email, subject, content);
    }

    @Override
    public void activate(ActivationRequestDto activationRequestDto) {
        UserEntity user = userService.findByEmail(activationRequestDto.getEmail());
        TokenEntity tokenEntity = tokenService.findByUserId(user);

        if (tokenEntity == null || !tokenEntity.getToken().equals(activationRequestDto.getToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid activation token");
        }

        if (tokenEntity.getExpireDate().before(new Date())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token has expired");
        }

        user.setActivatedAt(Timestamp.valueOf(LocalDateTime.now()));
        tokenEntity.setActive(true);
        tokenEntity.setUserId(user);

        tokenService.saveToken(tokenEntity);
        userService.createUser(user);
    }
}
