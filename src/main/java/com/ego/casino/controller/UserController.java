package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserDto> retrieveUser(@RequestHeader("X-USER-ID") Long userId) {
        UserDto userDto = userService.retrieveUser(userId).getBody();
        return ResponseEntity.ok(userDto);
    }

}
