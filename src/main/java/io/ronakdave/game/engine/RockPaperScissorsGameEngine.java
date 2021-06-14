package io.ronakdave.game.engine;

import java.util.Random;

import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.Shape;

public class RockPaperScissorsGameEngine {

    private Random random = new Random(System.currentTimeMillis());

    protected Shape generateShape() {
        // We use 8 because with 0 included that is 9 possibilities, equal weight for all
        Integer seed = random.nextInt(8);
        if(seed < 3) {
            // 0,1,2
            return Shape.ROCK;
        } else if (seed >= 3 || seed < 6) {
            // 3,4,5
            return Shape.PAPER;
        } else 
            // 6,7,8
            return Shape.SCISSORS;
    }

    protected GameResult evaluate(Shape playerShape, Shape computerShape) {
        if(playerShape == computerShape)
            return GameResult.DRAW;

        if(playerShape == Shape.ROCK && computerShape == Shape.PAPER) {
            return GameResult.LOSS;
        }

        if(playerShape == Shape.PAPER && computerShape == Shape.SCISSORS) {
            return GameResult.LOSS;
        }

        if(playerShape == Shape.SCISSORS && computerShape == Shape.ROCK) {
            return GameResult.LOSS;
        }

        return GameResult.WIN;
    }

    public GameEngineOutcome runEngine(Shape playerShape) {
        Shape computerShape = generateShape();
        return GameEngineOutcome.builder()
            .generatedShape(computerShape)
            .result(evaluate(playerShape, computerShape))
            .build();
    }
    
}
