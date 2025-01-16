package com.ego.casino.service;

import com.ego.casino.dto.GameDto;

import java.util.List;

public interface GameListingService {
    List<GameDto> getAllGames();
    GameDto getGameById(Long id);
}
