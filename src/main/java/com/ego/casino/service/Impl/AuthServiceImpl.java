package com.ego.casino.service.Impl;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.*;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AuthService;
import com.ego.casino.dto.*;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, String token) {

        String password = loginRequestDto.getPassword();
        String email = loginRequestDto.getEmail();

        UserEntity userEntity = userService.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!passwordEncoder.passwordEncoderBean().matches(password, userEntity.getPassword()) || userEntity.getEmail() == null) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        if (userEntity.getActivatedAt() == null) {
            throw new AccountAlreadyActivatedException("Account is not activated");
        }


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
        String username = registerRequestDto.getUsername();
        String password = registerRequestDto.getPassword();

        String subject = "Welcome to Bets10";
        String content = "Hello " + registerRequestDto.getUsername() + ",\n\nYour registration was successful. "
                + "Activate your account with this token:\n\n" + token + "\n\nThank you!";

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailFormatException("Email format is invalid");
        }

        if (password.length() < 8) {
            throw new WeakPasswordException("Password must be at least 8 characters long");
        }

        if (userService.findByEmail(email) != null) {
            throw new DuplicateEmailException("Email already exists");
        }

        if (userService.findByUsername(username) != null) {
            throw new DuplicateUsernameException("Username already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.passwordEncoderBean().encode(password));
        userService.createUser(userEntity);

        UserEntity savedUser = userService.findByEmail(email);
        if (savedUser == null) {
            throw new RuntimeException("User could not be saved or retrieved");
        }

        mailService.sendMail(email, subject, content);
    }

    @Override
    public void activate(ActivationRequestDto activationRequestDto) {
        UserEntity user = userService.findByEmail(activationRequestDto.getEmail());

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (user.getActivatedAt() != null) {
            throw new AccountAlreadyActivatedException("Account already activated");
        }

        try {
            user.setActivatedAt(Timestamp.valueOf(LocalDateTime.now()));
            userService.createUser(user);
        } catch (Exception e) {
            throw new ActivationFailedException("Error while activating the account.");
        }
    }
}
