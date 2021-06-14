package io.ronakdave.game.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResultSummary {
    private Player player;
    private GameResult result;
    private int played;
    private int won;
    private int lost;
    private int draw;
    private String gameResult;
}
