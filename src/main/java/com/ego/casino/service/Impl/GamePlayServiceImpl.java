package com.ego.casino.service.Impl;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.exception.ResourceNotFoundException;
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

    @Autowired
    private GameListingServiceImpl gameListingService;

    @Override
    public PlayGameResponseDto playGame(Long id, Long gameId , PlayGameRequestDto playGameRequestDto) {

        AccountEntity accountEntity = accountService.searchAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        GameEntity gameEntity = gameListingService.searchGame(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game not found!")
        );

        boolean result = isWinner(gameEntity.getWinChance().doubleValue());

        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = transactionService.calculateNewbalance(accountEntity.getBalance(), playGameRequestDto.getBetAmount(), gameEntity.getWinChance().doubleValue(), result);
        String status = result ? "WIN" : "LOSE";
        gameHistoryService.saveGameHistory(accountEntity, gameEntity, accountEntity.getBalance(), newBalance, playGameRequestDto, status);
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
