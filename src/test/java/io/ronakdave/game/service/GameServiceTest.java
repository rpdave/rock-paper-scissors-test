package io.ronakdave.game.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.ronakdave.game.engine.RockPaperScissorsGameEngine;
import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.repositories.PlayerRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private RockPaperScissorsGameEngine gameEngine;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameService gameService;

    private static Player mPlayer;
    @BeforeAll
    public static void setup() {
        mPlayer = Player.builder().id(1L).name("test").totalPlayed(0).lost(0).won(0).build();
    }

    @Test
    public void whenGameServiceSubmitRound_thenRunEngineToProduceValidResult() {
        when(gameEngine.runEngine(Shape.ROCK)).thenReturn(GameResult.WIN);
        when(playerRepository.findById(mPlayer.getId())).thenReturn(Optional.of(mPlayer));
        when(playerRepository.save(mPlayer)).thenReturn(mPlayer);

        GameResultSummary summary = gameService.playRound(Shape.ROCK, 1L);
        // summary should not be null
        assertNotNull(summary);
        // result should not be null
        assertNotNull(summary.getResult());
        // Player should not be null
        assertNotNull(summary.getPlayer());
    }

    @Test
    public void givenUserWithZeroRounds_whenGameServiceSubmitRoundWinResult_thenIncrementTotalCountAndWonCount() {
        when(gameEngine.runEngine(Shape.ROCK)).thenReturn(GameResult.WIN);
        when(playerRepository.findById(mPlayer.getId())).thenReturn(
            Optional.of(
                Player.builder()
                    .name("test")
                    .totalPlayed(0)
                    .lost(0)
                    .won(0)
                    .draw(0)
                    .build()
            )
        );

        GameResultSummary summary = gameService.playRound(Shape.ROCK, mPlayer.getId());

        assertTrue(summary.getPlayed() == 1);
        assertTrue(summary.getWon() == 1);
        assertTrue(summary.getDraw() == 0);
        assertTrue(summary.getLost() == 0);
    }

    @Test
    public void givenUserWithZeroGamesPlayed_whenGameServiceSubmitRoundLossResult_thenIncrementTotalCountAndLossCount() {
        when(gameEngine.runEngine(Shape.ROCK)).thenReturn(GameResult.LOSS);
        when(playerRepository.findById(mPlayer.getId())).thenReturn(
            Optional.of(
                Player.builder()
                    .name("test")
                    .totalPlayed(0)
                    .lost(0)
                    .won(0)
                    .draw(0)
                    .build()
            )
        );

        GameResultSummary summary = gameService.playRound(Shape.ROCK, mPlayer.getId());

        assertTrue(summary.getPlayed() == 1);
        assertTrue(summary.getLost() == 1);
        assertTrue(summary.getWon() == 0);
        assertTrue(summary.getDraw() == 0);

    }

    @Test
    public void givenUserWithZeroGamesPlayed_whenGameServiceSubmitRoundDrawResult_thenIncrementTotalCountAndDrawCount() {
        when(gameEngine.runEngine(Shape.ROCK)).thenReturn(GameResult.DRAW);
        when(playerRepository.findById(mPlayer.getId())).thenReturn(
            Optional.of(
                Player.builder()
                    .name("test")
                    .totalPlayed(0)
                    .lost(0)
                    .won(0)
                    .draw(0)
                    .build()
            )
        );

        GameResultSummary summary = gameService.playRound(Shape.ROCK, mPlayer.getId());

        assertTrue(summary.getPlayed() == 1);
        assertTrue(summary.getDraw() == 1);
        assertTrue(summary.getWon() == 0);
        assertTrue(summary.getLost() == 0);
    }
    
}
