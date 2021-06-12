package io.ronakdave.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ronakdave.game.request.UserAuthRequest;
import io.ronakdave.game.response.AuthResponse;

@RestController
public class AuthController {
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserAuthRequest userDetails) {
        // Just check if the player exists and then just return a success, perhaps set a cookie
        return ResponseEntity.ok(new AuthResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupUser(@RequestBody UserAuthRequest userDetails) {
        // Create a new player and save, return a authenticated cookie
        return ResponseEntity.ok(new AuthResponse());
    }
}
