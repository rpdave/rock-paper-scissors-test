package io.ronakdave.game.engine;

import io.ronakdave.game.model.GameResult;
import io.ronakdave.game.model.Shape;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameEngineOutcome {
    private GameResult result;
    private Shape generatedShape;
}
