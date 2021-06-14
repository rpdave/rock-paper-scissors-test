package io.ronakdave.game.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.ronakdave.game.engine.RockPaperScissorsGameEngine;
import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {
    private final RockPaperScissorsGameEngine gameEngine = new RockPaperScissorsGameEngine();
    private final PlayerRepository playerRepository;

    public GameResultSummary playRound(Shape playerShape, String username) {
        GameResult result = gameEngine.runEngine(playerShape);
        
        Player currentPlayer = playerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        int totalPlayed = currentPlayer.getGamesPlayed();
        int won = currentPlayer.getGamesWon();
        int lost = currentPlayer.getGamesLost();
        int draw = currentPlayer.getGamesDraw();

        totalPlayed += 1;
        
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

        currentPlayer.setGamesPlayed(totalPlayed);
        currentPlayer.setGamesLost(lost);
        currentPlayer.setGamesDraw(draw);
        currentPlayer.setGamesWon(won);

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
