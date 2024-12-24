package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //private ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserDto> retrieveBalance(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        UserDto userDto = new UserDto(user.getUsername(),user.getBalance());

        return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<GameHistoryDto> getHistory(String username) {
        return null;
    }

    /*
    @Override
    public UserDto EntitytoDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserEntity DtotoEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }

     */
}
