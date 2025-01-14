package com.ego.casino.service.Impl;

import com.ego.casino.configuration.PasswordEncoder;
import com.ego.casino.dto.*;
import com.ego.casino.entity.TokenEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AuthService;
import com.ego.casino.util.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String password = loginRequestDto.getPassword();
        String email = loginRequestDto.getEmail();
        Long userId = loginRequestDto.getUser().getId();

        UserEntity userEntity = userService.findByEmail(email);
        TokenEntity tokenEntity = tokenService.findByUserId(userId);

        if(!passwordEncoder.passwordEncoderBean().matches(password, userEntity.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
        return new LoginResponseDto(tokenEntity.getToken());
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
