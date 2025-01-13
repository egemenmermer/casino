package com.ego.casino.controller;

import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.service.Impl.UserServiceImpl;
import com.ego.casino.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/v1/login"))
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> createAuthenticationToken(@RequestBody LoginRequestDto loginRequestDto)
            throws Exception {
        validateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        UserEntity userEntity = authServiceImpl.getUserByEmail(loginRequestDto.getEmail());
        return ResponseEntity.ok(new LoginResponseDto(userEntity.getToken()));
    }

    private void validateUser(String email, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
