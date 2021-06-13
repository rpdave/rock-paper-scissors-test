package io.ronakdave.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.response.GameApiResponse;
import io.ronakdave.game.response.GameResultResponse;
import io.ronakdave.game.service.GameService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/startGame")
    public ResponseEntity<GameApiResponse> startGame() {
        // To start playing user must hit the endpoint first
        return ResponseEntity.ok(new GameApiResponse("game started"));
    }

    @PostMapping("/submit/{playerId}/{playerShape}")
    public ResponseEntity<GameResultResponse> submit(@PathVariable("playerId") int playerId, @PathVariable("playerShape") String playerShape) {

        try {
            Assert.isTrue(playerId > 0, "Invalid player id");
            Assert.notNull(playerShape, "Submit a player shape");
            Assert.isTrue(Shape.valueOf(playerShape) != null, "Submit a valid player shape");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new GameResultResponse("Invalid shape provided"));
        }
         catch (Exception e) {
            return ResponseEntity.badRequest().body(new GameResultResponse(e.getMessage()));
        }

        GameResultSummary summary = gameService.playRound(Shape.valueOf(playerShape), Long.valueOf(playerId));

        return  ResponseEntity.ok(new GameResultResponse(summary));
    }
}
