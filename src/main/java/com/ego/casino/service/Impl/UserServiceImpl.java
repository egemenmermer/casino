package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameDto;
import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.UserDto;
import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameHistoryRepository gameHistoryRepository;

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
    public List<GameHistoryDto> getHistory(String username) {

        List<GameHistoryEntity> gameHistoryEntities = gameHistoryRepository.findAll();

        return gameHistoryRepository
                .findAll()
                .stream()
                .map(gameHistoryEntity -> new GameHistoryDto(
                        gameHistoryEntity.getId(),
                        gameHistoryEntity.getGameName(),
                        gameHistoryEntity.getPlayDate(),
                        gameHistoryEntity.getBetAmount(),
                        gameHistoryEntity.getOldBalance(),
                        gameHistoryEntity.getNewBalance()
                ))
                .collect(Collectors.toList());
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
