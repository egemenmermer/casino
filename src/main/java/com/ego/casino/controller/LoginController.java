package com.ego.casino.controller;

import com.ego.casino.dto.LoginRequestDto;
import com.ego.casino.dto.LoginResponseDto;
import com.ego.casino.exception.AccountNotActivatedException;
import com.ego.casino.exception.InvalidCredentialsException;
import com.ego.casino.exception.UserNotFoundException;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.AuthServiceImpl;
import com.ego.casino.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @Operation(summary = "Login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto) {
        try {
            String newToken = jwtTokenUtil.generateToken(
                    new CustomUserDetails(loginRequestDto.getEmail(), loginRequestDto.getPassword())
            );
            LoginResponseDto loginResponseDto = authService.login(loginRequestDto, newToken);
            return ResponseEntity.ok(loginResponseDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LoginResponseDto(e.getMessage()));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDto(e.getMessage()));
        } catch (AccountNotActivatedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new LoginResponseDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDto("An unexpected error occurred!"));
        }

        //validateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        //UserEntity userEntity = authServiceImpl.getUserByEmail(loginRequestDto.getEmail());
        //TokenEntity tokenEntity = tokenServiceImpl.findByUserId(userEntity.getId());
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
