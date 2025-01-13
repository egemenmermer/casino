package com.ego.casino.service;

import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.dto.RegisterRequestDto;
import com.ego.casino.dto.RegisterResponseDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.security.CustomUserDetails;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void register(RegisterRequestDto registerRequestDto);
    public CustomUserDetails loadUserByEmail(String email);
}
