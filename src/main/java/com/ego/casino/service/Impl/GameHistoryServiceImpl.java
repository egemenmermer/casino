package com.ego.casino.service.Impl;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.service.GameHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {

    @Autowired
    GameHistoryRepository gameHistoryRepository;

    @Override
    public void saveGameHistory(AccountEntity accountEntity, GameEntity gameEntity, BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto){
        GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
        gameHistoryEntity.setGame(gameEntity);
        gameHistoryEntity.setAccount(accountEntity);
        gameHistoryEntity.setOldBalance(oldBalance);
        gameHistoryEntity.setNewBalance(newBalance);
        gameHistoryEntity.setBetAmount(BigDecimal.valueOf(playGameRequestDto.getBetAmount()));
        gameHistoryEntity.setPlayDate(Timestamp.valueOf(LocalDateTime.now()));
        gameHistoryRepository.save(gameHistoryEntity);
    }
}
