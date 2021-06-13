package io.ronakdave.game.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ronakdave.game.model.Player;
import io.ronakdave.game.service.PlayerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/list")
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }
}
