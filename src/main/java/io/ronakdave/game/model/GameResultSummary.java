package io.ronakdave.game.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResultSummary {
    private Player player;
    private GameResult result;
    private Integer wonCount;
    private Integer lossCount;
    private Integer totalCount;
}
