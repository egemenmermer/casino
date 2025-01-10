package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.service.GameListingService;
import com.ego.casino.service.Impl.GameListingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/games")
@RestController
public class GameListingController {

    @Autowired
    GameListingServiceImpl gameListingService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames(){
        List<GameDto> games = gameListingService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
