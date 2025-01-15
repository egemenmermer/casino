package com.ego.casino.service;

import com.ego.casino.dto.ActivationRequestDto;
import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.dto.RegisterRequestDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.security.CustomUserDetails;
import org.springframework.security.core.userdetails.User;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void register(RegisterRequestDto registerRequestDto, String token);
    void activate(ActivationRequestDto activationRequestDto);
}
