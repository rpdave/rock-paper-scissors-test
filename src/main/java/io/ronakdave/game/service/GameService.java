package io.ronakdave.game.service;

import java.util.NoSuchElementException;

import io.ronakdave.game.engine.RockPaperScissorsGameEngine;
import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameService {
    private final RockPaperScissorsGameEngine gameEngine;
    private final PlayerRepository playerRepository;

    public GameResultSummary playRound(Shape playerShape, Long playerId) throws NoSuchElementException {
        // Pass shape to engine
        // get result
        GameResult result = gameEngine.runEngine(playerShape);
        // Get the player from repository
        Player currentPlayer = playerRepository.findById(playerId).orElseThrow();
        // calc player win, loss, played metrics
        // create result object
        // return result
        return GameResultSummary.builder()
            .result(result)
            .player(currentPlayer)
            .lossCount(currentPlayer.getLost())
            .totalCount(currentPlayer.getTotalPlayed())
            .wonCount(currentPlayer.getWon())
            .build();
    }

    
}
