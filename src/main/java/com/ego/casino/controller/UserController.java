package com.ego.casino.controller;

import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.UserNotFoundException;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/me")
    @Operation(summary = "Get Me")
    public ResponseEntity<UserDto> getMe(
            @CurrentUser CustomUserDetails currentUser) {
        try {
            UserEntity user = userService.getUserByEmail(currentUser.getEmail());
            return ResponseEntity.ok(new UserDto(user.getId(), currentUser.getEmail()));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found for email: " + currentUser.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred while fetching user details");
        }
    }



    /*
    @GetMapping("/me")
    @Operation(summary = "Get Me")
    public ResponseEntity<?> getMe(@CurrentUser CustomUserDetails currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok("Logged in as: " + currentUser.getEmail());
    }

     */




}
