package com.ego.casino.service.Impl;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.service.AccountService;
import com.ego.casino.service.GameHistoryService;
import com.ego.casino.service.GamePlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GamePlayServiceImpl implements GamePlayService {

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    GameHistoryServiceImpl gameHistoryService;

    @Override
    public PlayGameResponseDto playGame(Long id, PlayGameRequestDto playGameRequestDto) {

        AccountEntity accountEntity = accountService.searchAccount(playGameRequestDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        GameEntity gameEntity = new GameEntity();

        boolean result = isWinner(gameEntity.getWinChance().doubleValue());


        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = transactionService.calculateNewbalance(accountEntity.getBalance(), playGameRequestDto.getBetAmount(), gameEntity.getWinChance().doubleValue(), result);
        gameHistoryService.saveGameHistory(accountEntity, gameEntity, accountEntity.getBalance(), newBalance, playGameRequestDto, gameEntity, gameEntity.getName());
        accountService.updateUserBalance(accountEntity, newBalance);

        if(result) {
            return new PlayGameResponseDto("You win!", oldBalance, newBalance);
        }else{
            return new PlayGameResponseDto("You lost!", oldBalance, newBalance);
        }
    }


    public boolean isWinner(double winRate){
        Double randomValue = Math.random();
        return randomValue < winRate;

    }
}
