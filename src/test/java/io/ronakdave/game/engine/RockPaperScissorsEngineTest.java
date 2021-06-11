package io.ronakdave.game.engine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.Shape;

public class RockPaperScissorsEngineTest {

    RockPaperScissorsGameEngine gameEngine = new RockPaperScissorsGameEngine();

    @Test
    public void whenGenerateShape_thenComputerGenerateValidShape() {
        Shape validShape = gameEngine.generateShape();

        assertNotNull(validShape);
        assertTrue(validShape instanceof Shape);
    }

    @Test
    public void whenEvaluateSameShape_thenProduceDrawResult() {
        GameResult result = gameEngine.evaluate(Shape.PAPER, Shape.PAPER);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.DRAW);
    }

    @Test
    public void whenEvaluateRockVsPaper_thenProduceLossResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.ROCK, Shape.PAPER);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.LOSS);
    }

    @Test
    public void whenEvaluatePaperVsScissors_thenProduceLossResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.PAPER, Shape.SCISSORS);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.LOSS);
    }

    @Test
    public void whenEvaluateScissorsVsRock_thenProduceLossResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.SCISSORS, Shape.ROCK);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.LOSS);
    }

    @Test
    public void whenEvaluateRockVsScissors_thenProduceWinResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.ROCK, Shape.SCISSORS);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.WIN);
    }

    @Test
    public void whenEvaluatePaperVsRock_thenProduceWinResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.PAPER, Shape.ROCK);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.WIN);
    }

    @Test
    public void whenEvaluateScissorsVsPaper_thenProduceWinResultForPlayer() {
        GameResult result = gameEngine.evaluate(Shape.SCISSORS, Shape.PAPER);

        assertNotNull(result);
        assertTrue(result instanceof GameResult);
        assertTrue(result == GameResult.WIN);
    }
}
