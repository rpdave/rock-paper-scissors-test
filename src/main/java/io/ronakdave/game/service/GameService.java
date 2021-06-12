package io.ronakdave.game.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import io.ronakdave.game.engine.RockPaperScissorsGameEngine;
import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {
    private final RockPaperScissorsGameEngine gameEngine;
    private final PlayerRepository playerRepository;

    public GameResultSummary playRound(Shape playerShape, Long playerId) throws NoSuchElementException {
        GameResult result = gameEngine.runEngine(playerShape);
        
        Player currentPlayer = playerRepository.findById(playerId).orElseThrow();

        int totalPlayed = currentPlayer.getTotalPlayed();
        int won = currentPlayer.getWon();
        int lost = currentPlayer.getLost();
        int draw = currentPlayer.getDraw();

        totalPlayed += 1;
        currentPlayer.setTotalPlayed(totalPlayed);
        
        switch (result) {
            case WIN:
                won += 1;
                break;
            case LOSS:
                lost += 1;
                break;
            case DRAW:
                draw += 1;
                break;
        }

        currentPlayer.setTotalPlayed(totalPlayed);
        currentPlayer.setLost(lost);
        currentPlayer.setDraw(draw);
        currentPlayer.setWon(won);

        currentPlayer = playerRepository.save(currentPlayer);

        return GameResultSummary.builder()
            .result(result)
            .player(currentPlayer)
            .played(totalPlayed)
            .won(won)
            .lost(lost)
            .draw(draw)
            .build();
    }

    
}
