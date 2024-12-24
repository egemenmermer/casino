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
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameHistoryRepository gameHistoryRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(gameEntity -> modelMapper.map(gameEntity, GameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlayGameResponseDto playGame(PlayGameRequestDto playGameRequestDto) {

        UserEntity userEntity = userRepository.findByUsername(playGameRequestDto.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("Username: " + playGameRequestDto.getUsername() + "not found")
        );

        GameEntity gameEntity = gameRepository.findByName(playGameRequestDto.getGameName()).orElseThrow(
                () -> new ResourceNotFoundException("Game: " + playGameRequestDto.getGameName() + "not found")
        );

        boolean result = isWinner(gameEntity.getWinChance().doubleValue());

        BigDecimal oldBalance = userEntity.getBalance();
        BigDecimal newBalance = calculateNewbalance(userEntity.getBalance(), playGameRequestDto.getBetAmount(), gameEntity.getWinChance().doubleValue(), result);
        saveGameHistory(userEntity.getBalance(), newBalance, playGameRequestDto, gameEntity);
        updateUserBalance(userEntity, newBalance);

        return new PlayGameResponseDto("You win!", oldBalance, newBalance);
    }


    public boolean isWinner(double winRate){
        Double randomValue = Math.random();
        return randomValue < winRate;
    }

    public double calculateWinAmount(double betAmount, double winRate){
        return betAmount * (100 / winRate);
    }

    private BigDecimal calculateNewbalance(BigDecimal currentBalance, double betAmount, double winRate, boolean isWinner){
        if(isWinner){
            double winAmount = calculateWinAmount(betAmount, winRate);
            return currentBalance.add(BigDecimal.valueOf(winAmount));
        }else{
            return currentBalance.subtract(BigDecimal.valueOf(betAmount));
        }
    }

    private void saveGameHistory(BigDecimal oldBalance, BigDecimal newBalance, PlayGameRequestDto playGameRequestDto, GameEntity gameEntity ){
        GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
        gameHistoryEntity.setOldBalance(oldBalance);
        gameHistoryEntity.setNewBalance(newBalance);
        gameHistoryEntity.setBetAmount(BigDecimal.valueOf(playGameRequestDto.getBetAmount()));
        gameHistoryEntity.setGameName(playGameRequestDto.getGameName());
        gameHistoryEntity.setPlayDate(Timestamp.valueOf(LocalDateTime.now()));
        gameHistoryRepository.save(gameHistoryEntity);
    }

    private void updateUserBalance(UserEntity userEntity, BigDecimal newBalance){
        userEntity.setBalance(newBalance);
        userEntity.setUsername(userEntity.getUsername());
        userRepository.save(userEntity);
    }
}
