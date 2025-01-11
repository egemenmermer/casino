package com.ego.casino.service;

import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;

import java.math.BigDecimal;
import java.util.List;

public interface GameHistoryService {

    public void createGameHistory(AccountEntity accountEntity, GameEntity gameEntity, BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto, String status);
    public List<GameHistoryDto> getHistory(Long accountId);
}
