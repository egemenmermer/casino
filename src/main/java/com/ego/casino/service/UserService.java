package com.ego.casino.service;

import com.ego.casino.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<UserDto> getUser(Long id);


}
