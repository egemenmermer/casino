package com.ego.casino.controller;

import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/v1/login"))
public class LoginController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        final String newToken = jwtTokenUtil.generateToken(new CustomUserDetails(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        //validateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        //UserEntity userEntity = authServiceImpl.getUserByEmail(loginRequestDto.getEmail());
        //TokenEntity tokenEntity = tokenServiceImpl.findByUserId(userEntity.getId());
        return ResponseEntity.ok(authService.login(loginRequestDto, newToken));
    }

    /*
    private void validateUser(String email, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

     */
}
