package com.ego.casino.controller;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.service.GamePlayService;
import com.ego.casino.service.GameService;
import com.ego.casino.service.Impl.GamePlayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GamePlayController {

    @Autowired
    private GamePlayServiceImpl gamePlayService;

    @PostMapping("/play/{gameId}")
    public ResponseEntity<PlayGameResponseDto> playGame(@RequestHeader("X-USER-ID")Long id,@PathVariable Long gameId, @RequestBody PlayGameRequestDto playGameRequestDto){
        PlayGameResponseDto playGameResponseDto = gamePlayService.playGame(id, gameId, playGameRequestDto);
        return new ResponseEntity<>(playGameResponseDto, HttpStatus.OK);
    }
}
