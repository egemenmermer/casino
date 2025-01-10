package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.entity.GameHistoryEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.GameHistoryRepository;
import com.ego.casino.service.GameHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameHistoryServiceImpl implements GameHistoryService {

    @Autowired
    GameHistoryRepository gameHistoryRepository;

    @Autowired
    AccountServiceImpl accountService;

    @Override
    public void saveGameHistory(AccountEntity accountEntity, GameEntity gameEntity, BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto, String status) {
        GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
        gameHistoryEntity.setGame(gameEntity);
        gameHistoryEntity.setAccount(accountEntity);
        gameHistoryEntity.setOldBalance(oldBalance);
        gameHistoryEntity.setNewBalance(newBalance);
        gameHistoryEntity.setBetAmount(BigDecimal.valueOf(playGameRequestDto.getBetAmount()));
        gameHistoryEntity.setPlayDate(Timestamp.valueOf(LocalDateTime.now()));
        gameHistoryEntity.setStatus(status);
        gameHistoryRepository.save(gameHistoryEntity);
    }

    @Override
    public List<GameHistoryDto> getHistory(Long id) {

        AccountEntity accountEntity = accountService.searchAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        return gameHistoryRepository
                .findByAccountId(accountEntity.getId())
                .stream()
                .map(gameHistoryEntity -> new GameHistoryDto(
                        gameHistoryEntity.getId(),
                        gameHistoryEntity.getPlayDate(),
                        gameHistoryEntity.getBetAmount(),
                        gameHistoryEntity.getOldBalance(),
                        gameHistoryEntity.getNewBalance(),
                        gameHistoryEntity.getStatus(),
                        gameHistoryEntity.getGame()
                ))
                .collect(Collectors.toList());
    }
}
