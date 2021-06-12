package io.ronakdave.game.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GameApiResponse {
    private final String message;
}
