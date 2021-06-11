package io.ronakdave.game.engine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.ronakdave.game.model.Shape;

public class RockPaperScissorsEngineTest {

    RockPaperScissorsGameEngine gameEngine = new RockPaperScissorsGameEngine();

    @Test
    public void whenGenerateShape_thenComputerGenerateValidShape() {
        Shape validShape = gameEngine.generateShape();

        assertNotNull(validShape);
        assertTrue(validShape instanceof Shape);
    }
}
