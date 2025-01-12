package com.ego.casino.service.Impl;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.service.GamePlayService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Transactional
    @Override
    public PlayGameResponseDto playGame(Long id, Long gameId , PlayGameRequestDto playGameRequestDto) {

        AccountEntity accountEntity = accountService.findAccount(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found!")
        );
        GameEntity gameEntity = gameListingService.findGame(gameId).orElseThrow(
                () -> new ResourceNotFoundException("Game not found!")
        );

        boolean result = isWinner(gameEntity.getWinChance().doubleValue());

        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = calculateNewBalance(accountEntity.getBalance(), playGameRequestDto.getBetAmount(), gameEntity.getWinChance().doubleValue(), result);
        String status = result ? "WIN" : "LOSE";

        gameHistoryService.createGameHistory(accountEntity, gameEntity, accountEntity.getBalance(), newBalance, playGameRequestDto, status);
        transactionService.createTransaction(accountEntity, BigDecimal.valueOf(playGameRequestDto.getBetAmount()), newBalance, TransactionType.BET, LocalDateTime.now());
        accountService.updateBalance(accountEntity, newBalance);

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

    public double calculateWinAmount(double betAmount, double winRate){
        return betAmount * (1 / winRate);
    }

    public BigDecimal calculateNewBalance(BigDecimal currentBalance, double betAmount, double winRate, boolean isWinner){
        if(isWinner){
            double winAmount = calculateWinAmount(betAmount, winRate);
            return currentBalance.add(BigDecimal.valueOf(winAmount));
        }else{
            return currentBalance.subtract(BigDecimal.valueOf(betAmount));
        }
    }
}
