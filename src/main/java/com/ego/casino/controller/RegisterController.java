package com.ego.casino.controller;

import com.ego.casino.dto.RegisterRequestDto;
import com.ego.casino.dto.RegisterResponseDto;
import com.ego.casino.exception.DuplicateEmailException;
import com.ego.casino.exception.DuplicateUsernameException;
import com.ego.casino.exception.InvalidEmailFormatException;
import com.ego.casino.exception.WeakPasswordException;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<RegisterResponseDto> register(
            @RequestBody RegisterRequestDto registerRequestDto) {
        try {
            final String token = jwtTokenUtil.generateToken(new CustomUserDetails(registerRequestDto.getEmail(), registerRequestDto.getPassword()));
            authService.register(registerRequestDto, token);
            return ResponseEntity.ok(new RegisterResponseDto(token, "Registration successful!"));
        } catch (InvalidEmailFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponseDto(e.getMessage()));
        } catch (WeakPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponseDto(e.getMessage()));
        } catch (DuplicateEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegisterResponseDto(e.getMessage()));
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RegisterResponseDto(e.getMessage()));
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterResponseDto("An unexpected error occurred"));
        }
    }
}
