package com.ego.casino.controller;

import com.ego.casino.dto.RegisterRequestDto;
import com.ego.casino.dto.RegisterResponseDto;
import com.ego.casino.service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    @Autowired
    private AuthServiceImpl authService;

    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto registerRequestDto) {
        authService.register(registerRequestDto);
        return ResponseEntity.ok(new RegisterResponseDto());
    }
}
