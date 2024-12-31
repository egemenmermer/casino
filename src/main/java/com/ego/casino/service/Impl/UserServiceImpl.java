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
    @Autowired
    private GameHistoryRepository gameHistoryRepository;


    @Override
    public ResponseEntity<UserDto> retrieveBalance(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        UserDto userDto = new UserDto(user.getId(),user.getUsername(),user.getBalance());

        return ResponseEntity.ok(userDto);
    }

    @Override
    public List<GameHistoryDto> getHistory(String username) {

        return gameHistoryRepository
                .findAll()
                .stream()
                .map(gameHistoryEntity -> new GameHistoryDto(
                        gameHistoryEntity.getHistoryId(),
                        gameHistoryEntity.getGameName(),
                        gameHistoryEntity.getPlayDate(),
                        gameHistoryEntity.getBetAmount(),
                        gameHistoryEntity.getOldBalance(),
                        gameHistoryEntity.getNewBalance()
                ))
                .collect(Collectors.toList());
    }

}
