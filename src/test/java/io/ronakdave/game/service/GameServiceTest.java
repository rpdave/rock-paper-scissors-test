package io.ronakdave.game.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setup() {
        when(gameEngine.runEngine(Shape.ROCK)).thenReturn(GameResult.WIN);
        when(playerRepository.findById(1L)).thenReturn(
            Optional.of(
                Player.builder()
                    .name("test")
                    .totalPlayed(0)
                    .lost(0)
                    .won(0)
                    .build()
            )
        );
    }

    @Test
    public void whenGameServiceSubmitRound_thenRunEngineToProduceValidResult() {
        GameResultSummary summary = gameService.playRound(Shape.ROCK, 1L);
        // summary should not be null
        assertNotNull(summary);
        // result should not be null
        assertNotNull(summary.getResult());
        // Player should not be null
        assertNotNull(summary.getPlayer());
        // Any number should not be null
        assertNotNull(summary.getLossCount());
        assertNotNull(summary.getWonCount());
        assertNotNull(summary.getTotalCount());
    }
    
}
