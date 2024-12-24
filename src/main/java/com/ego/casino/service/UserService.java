package com.ego.casino.service;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public ResponseEntity<UserDto> retrieveBalance(String username);
    public List<GameHistoryDto> getHistory(String username);


    //public UserDto EntitytoDto(UserEntity userEntity);
    //public UserEntity DtotoEntity(UserDto userDto);
}
