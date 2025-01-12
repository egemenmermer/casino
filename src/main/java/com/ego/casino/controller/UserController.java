package com.ego.casino.controller;

import com.ego.casino.dto.UserDto;
import com.ego.casino.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/me")
    @Operation(summary = "Get Me")
    public ResponseEntity<UserDto> retrieveUser(@RequestHeader("X-USER-ID") Long userId) {
        UserDto userDto = userService.getUser(userId).getBody();
        return ResponseEntity.ok(userDto);
    }

}
