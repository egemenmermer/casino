package com.ego.casino.service;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;

import java.math.BigDecimal;

public interface GameHistoryService {

    public void saveGameHistory(AccountEntity accountEntity, GameEntity gameEntity, BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto);
}
