package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.exception.ResourceNotFoundException;
import com.ego.casino.service.GameListingService;
import com.ego.casino.service.Impl.GameListingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/games")
@RestController
public class GameListingController {

    @Autowired
    GameListingServiceImpl gameListingService;

    @GetMapping
    @Operation(summary = "Get All Games")
    public ResponseEntity<List<GameDto>> getAllGames() {
        try {
            List<GameDto> games = gameListingService.getAllGames();
            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch games: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Game by ID")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        try {
            GameDto game = gameListingService.getGameById(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Game not found with ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch game: " + e.getMessage());
        }
    }
}
