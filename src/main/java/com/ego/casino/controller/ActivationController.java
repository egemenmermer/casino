package com.ego.casino.controller;

import com.ego.casino.dto.ActivationRequestDto;
import com.ego.casino.dto.ActivationResponseDto;
import com.ego.casino.dto.RegisterRequestDto;
import com.ego.casino.dto.RegisterResponseDto;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.AuthService;
import com.ego.casino.service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activate")
public class ActivationController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping
    public ResponseEntity<ActivationResponseDto> activate(@RequestBody ActivationRequestDto activationRequestDto) {
        authService.activate(activationRequestDto);
        return ResponseEntity.ok(new ActivationResponseDto("Account Activated!"));
    }
}
