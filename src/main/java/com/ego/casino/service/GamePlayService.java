package com.ego.casino.service;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;

public interface GamePlayService {

    public PlayGameResponseDto playGame(Long id,Long gameId, PlayGameRequestDto playGameRequestDto);
}
