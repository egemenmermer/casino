package com.ego.casino.service;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.security.CustomUserDetails;

public interface GamePlayService {

    public PlayGameResponseDto playGame(CustomUserDetails userDetails, Long accountId, Long gameId, PlayGameRequestDto playGameRequestDto);
}
