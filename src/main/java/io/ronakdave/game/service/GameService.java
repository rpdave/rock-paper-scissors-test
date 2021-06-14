package io.ronakdave.game.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.ronakdave.game.engine.GameEngineOutcome;
import io.ronakdave.game.engine.RockPaperScissorsGameEngine;
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
        String gameCommentary = username + " played " + playerShape.name();
        GameEngineOutcome result = gameEngine.runEngine(playerShape);
        gameCommentary = gameCommentary +", The computer has played " + result.getGeneratedShape().name();
        
        Player currentPlayer = playerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        int totalPlayed = currentPlayer.getGamesPlayed();
        int won = currentPlayer.getGamesWon();
        int lost = currentPlayer.getGamesLost();
        int draw = currentPlayer.getGamesDraw();

        totalPlayed += 1;
        
        switch (result.getResult()) {
            case WIN:
                gameCommentary += ", The player wins!! Human brains are still superior!!";
                won += 1;
                break;
            case LOSS:
                gameCommentary += ", The computer wins!! The machines are taking over!!";
                lost += 1;
                break;
            case DRAW:
                gameCommentary += ", What a close match up! its a draw!";
                draw += 1;
                break;
        }

        currentPlayer.setGamesPlayed(totalPlayed);
        currentPlayer.setGamesLost(lost);
        currentPlayer.setGamesDraw(draw);
        currentPlayer.setGamesWon(won);

        currentPlayer = playerRepository.save(currentPlayer);

        return GameResultSummary.builder()
            .gameResult(gameCommentary)
            .result(result.getResult())
            .player(currentPlayer)
            .played(totalPlayed)
            .won(won)
            .lost(lost)
            .draw(draw)
            .build();
    }

    
}
