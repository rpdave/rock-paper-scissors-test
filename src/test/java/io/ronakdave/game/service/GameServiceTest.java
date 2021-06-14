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

import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.GameResultSummary;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.model.Shape;
import io.ronakdave.game.repository.PlayerRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameService gameService;

    private static Player mPlayer;
    @BeforeAll
    public static void setup() {
        mPlayer = Player.builder().id(1L).username("test").gamesPlayed(0).gamesLost(0).gamesWon(0).gamesDraw(0).build();
    }

    @Test
    public void whenGameServiceSubmitRound_thenRunEngineToProduceValidResult() {
        when(playerRepository.findByUsername(mPlayer.getUsername())).thenReturn(Optional.of(mPlayer));
        when(playerRepository.save(mPlayer)).thenReturn(mPlayer);

        GameResultSummary summary = gameService.playRound(Shape.ROCK, "test");
        // summary should not be null
        assertNotNull(summary);
        // result should not be null
        assertNotNull(summary.getResult());
        // Player should not be null
        assertNotNull(summary.getPlayer());
    }

    @Test
    public void givenUserWithZeroRounds_whenGameServiceSubmitRoundWinResult_thenIncrementTotalCountAndWonCount() {
        when(playerRepository.findByUsername(mPlayer.getUsername())).thenReturn(Optional.of(mPlayer));
        when(playerRepository.save(mPlayer)).thenReturn(mPlayer);

        GameResultSummary summary = gameService.playRound(Shape.ROCK, mPlayer.getUsername());

        assertNotNull(summary);
        assertNotNull(summary.getResult());
        assertNotNull(summary.getPlayer());

        if(summary.getResult() == GameResult.WIN)
            assertTrue(summary.getWon() > 0);

        if(summary.getResult() == GameResult.LOSS) 
            assertTrue(summary.getLost() > 0);
        
        if(summary.getResult() == GameResult.DRAW)
            assertTrue(summary.getDraw() > 0);

        assertTrue(summary.getPlayed() > 0);
    }
    
}
