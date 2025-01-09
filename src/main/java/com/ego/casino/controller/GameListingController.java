package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.service.GameListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameListingController {

    @Autowired
    GameListingService gameListingService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames(){
        List<GameDto> games = gameListingService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
