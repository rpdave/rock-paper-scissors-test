package io.ronakdave.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.response.GameResultResponse;
import io.ronakdave.game.service.GameService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;

    @GetMapping("/submit/{playerShape}")
    public ResponseEntity<GameResultResponse> submit(@PathVariable("playerShape") String playerShape, Authentication authentication) {

        try {
            Assert.notNull(playerShape, "submitted shape must not be null");
            Assert.isTrue(Shape.getShape(playerShape) != null, "shape is not valid, valid shapes are rock, paper, scissors");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new GameResultResponse(e.getMessage()));
        }

        GameResultSummary summary = gameService.playRound(Shape.getShape(playerShape), authentication.getName());

        return  ResponseEntity.ok(new GameResultResponse(summary));
    }
}
