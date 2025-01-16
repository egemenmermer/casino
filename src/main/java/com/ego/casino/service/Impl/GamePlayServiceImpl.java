package com.ego.casino.service.Impl;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.entity.UserEntity;
import com.ego.casino.enums.TransactionType;
import com.ego.casino.exception.*;
import com.ego.casino.security.CustomUserDetails;
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
    UserServiceImpl userService;

    @Autowired
    private GameListingServiceImpl gameListingService;

    @Transactional
    @Override
    public PlayGameResponseDto playGame(CustomUserDetails userDetails, Long accountId, Long gameId, PlayGameRequestDto playGameRequestDto) {
        UserEntity userEntity = userService.getUserByEmail(userDetails.getEmail());
        AccountEntity accountEntity = accountService.findAccountByUserId(userEntity, accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for this user"));

        GameEntity gameEntity = gameListingService.findGame(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game not found!"));

        if (playGameRequestDto.getBetAmount() <= 0) {
            throw new InvalidBetAmountException("Bet amount must be greater than zero.");
        }

        if (accountEntity.getBalance().compareTo(BigDecimal.valueOf(playGameRequestDto.getBetAmount())) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to place the bet.");
        }

        boolean result = isWinner(gameEntity.getWinChance().doubleValue());

        BigDecimal oldBalance = accountEntity.getBalance();
        BigDecimal newBalance = calculateNewBalance(accountEntity.getBalance(), playGameRequestDto.getBetAmount(), gameEntity.getWinChance().doubleValue(), result);
        String status = result ? "WIN" : "LOSE";

        gameHistoryService.createGameHistory(accountEntity, gameEntity, accountEntity.getBalance(), newBalance, playGameRequestDto, status);
        transactionService.createTransaction(accountEntity, BigDecimal.valueOf(playGameRequestDto.getBetAmount()), newBalance, TransactionType.BET, LocalDateTime.now());
        accountService.updateBalance(accountEntity, newBalance);

        return new PlayGameResponseDto(
                result ? "You win!" : "You lost!",
                oldBalance,
                newBalance
        );
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
