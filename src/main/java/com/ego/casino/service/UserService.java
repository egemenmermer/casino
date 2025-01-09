package com.ego.casino.service;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<UserDto> retrieveUser(Long id);


}
