package io.ronakdave.game.engine;

import java.util.Random;

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
    
}
