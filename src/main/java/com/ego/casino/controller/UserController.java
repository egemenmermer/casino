package com.ego.casino.controller;

import com.ego.casino.dto.UserDto;
import com.ego.casino.security.CurrentUser;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.Impl.UserServiceImpl;
import com.ego.casino.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /*
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(@CurrentUser CustomUserDetails currentUser) {
        return ResponseEntity.ok(new UserDto(userService.getUserByEmail(currentUser.getEmail()).getId(), currentUser.getEmail()));
    }

     */




    @GetMapping("/me")
    @Operation(summary = "Get Me")
    public ResponseEntity<?> getMe(@CurrentUser CustomUserDetails currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok("Logged in as: " + currentUser.getEmail());
    }




}
