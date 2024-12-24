package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames(){
        List<GameDto> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
