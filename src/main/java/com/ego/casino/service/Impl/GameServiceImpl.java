package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameDto;
import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.repository.GameRepository;
import com.ego.casino.repository.UserRepository;
import com.ego.casino.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameHistoryRepository gameHistoryRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }






    private void saveGameHistory(UserEntity userId, GameEntity gameId, BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto, GameEntity gameEntity , String gameName ){
        GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
        gameHistoryEntity.setGame(gameId);
        gameHistoryEntity.setUser(userId);
        gameHistoryEntity.setOldBalance(oldBalance);
        gameHistoryEntity.setNewBalance(newBalance);
        gameHistoryEntity.setBetAmount(BigDecimal.valueOf(playGameRequestDto.getBetAmount()));
        gameHistoryEntity.setGameName(gameName);
        gameHistoryEntity.setPlayDate(Timestamp.valueOf(LocalDateTime.now()));
        gameHistoryRepository.save(gameHistoryEntity);
    }


}
