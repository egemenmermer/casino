package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        UserDto userDto = new UserDto(user.getId(),user.getUsername());
        return ResponseEntity.ok(userDto);
    }



}
