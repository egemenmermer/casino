package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameDto;
import com.ego.casino.entity.GameEntity;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.repository.GameRepository;
import com.ego.casino.service.GameListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameListingServiceImpl implements GameListingService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(gameEntity -> new GameDto(
                        gameEntity.getId(),
                        gameEntity.getName(),
                        gameEntity.getWinChance(),
                        gameEntity.getMinAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public GameDto getGameById(Long id) {
        GameEntity gameEntity = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with ID: " + id));

        return new GameDto(
                gameEntity.getId(),
                gameEntity.getName(),
                gameEntity.getWinChance(),
                gameEntity.getMinAmount()
        );
    }

    public Optional<GameEntity> findGame(Long id) {
        return gameRepository.findById(id);
    }
}
