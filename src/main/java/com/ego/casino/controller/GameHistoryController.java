package com.ego.casino.controller;

import com.ego.casino.dto.GameDto;
import com.ego.casino.dto.GameHistoryDto;
import com.ego.casino.service.GameHistoryService;
import com.ego.casino.service.Impl.GameHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game_history")
public class GameHistoryController {

    @Autowired
    GameHistoryServiceImpl gameHistoryService;


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GameHistoryDto>> getHistory(@RequestParam Long account_id, @RequestHeader("X-USER-ID") Long userId) {
        List<GameHistoryDto> games = gameHistoryService.getHistory(account_id);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
