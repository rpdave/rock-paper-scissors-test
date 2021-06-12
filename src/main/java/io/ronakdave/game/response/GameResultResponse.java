package io.ronakdave.game.response;

import io.ronakdave.game.model.GameResultSummary;

public class GameResultResponse {
    private GameResultSummary summary;
    private String message;

    public GameResultResponse(String message) {
        this.message = message;
    }

    public GameResultResponse(GameResultSummary summary) {
        this.summary = summary;
    }
}
