package com.ego.casino.service.Impl;

import com.ego.casino.dto.GameDto;
import com.ego.casino.entity.AccountEntity;
import com.ego.casino.entity.GameEntity;
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
        List<GameEntity> gameEntities = gameRepository.findAll();
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

    public Optional<GameEntity> searchGame(Long id) {
        return gameRepository.findById(id);
    }
}
