package com.ego.casino.controller;

import com.ego.casino.dto.UserDto;
import com.ego.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}/balance")
    public ResponseEntity<UserDto> retrieveBalance(@PathVariable String username) {

        UserDto userDto = userService.retrieveBalance(username).getBody();
        return ResponseEntity.ok(userDto);

    }


}
