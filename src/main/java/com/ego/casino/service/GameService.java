package com.ego.casino.service;

import com.ego.casino.dto.GameDto;
import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;

import java.util.List;

public interface GameService {

    public List<GameDto> getAllGames();
    public PlayGameResponseDto playGame(String gameName, PlayGameRequestDto playGameRequestDto);


}
