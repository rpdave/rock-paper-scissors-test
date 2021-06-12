package io.ronakdave.game.request;

import lombok.Data;

@Data
public class UserAuthRequest {
    private String username;
    private String password;
}
