package com.ego.casino.controller;

import com.ego.casino.dto.PlayGameRequestDto;
import com.ego.casino.dto.PlayGameResponseDto;
import com.ego.casino.exception.*;
import com.ego.casino.security.CurrentUser;
import com.ego.casino.security.CustomUserDetails;
import com.ego.casino.service.GamePlayService;

import com.ego.casino.service.Impl.GamePlayServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Play Game By GameId")
    public ResponseEntity<PlayGameResponseDto> playGame(
            @CurrentUser CustomUserDetails currentUser,
            @RequestParam Long account_id,
            @PathVariable Long gameId,
            @RequestBody PlayGameRequestDto playGameRequestDto){

        try {
            PlayGameResponseDto playGameResponseDto = gamePlayService.playGame(currentUser, account_id, gameId, playGameRequestDto);
            return new ResponseEntity<>(playGameResponseDto, HttpStatus.OK);
        } catch (GameNotFoundException | AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PlayGameResponseDto(e.getMessage()));
        } catch (InsufficientBalanceException | InvalidBetAmountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PlayGameResponseDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PlayGameResponseDto("An unexpected error occurred!"));
        }
    }
}
