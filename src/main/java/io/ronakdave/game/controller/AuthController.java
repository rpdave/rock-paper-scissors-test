package io.ronakdave.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ronakdave.game.request.UserAuthRequest;
import io.ronakdave.game.response.AuthResponse;
import io.ronakdave.game.service.PlayerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final PlayerService playerService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserAuthRequest userDetails) {
        // Just check if the player exists and then just return a success, perhaps set a cookie
        return ResponseEntity.ok(new AuthResponse("success"));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupUser(@RequestBody UserAuthRequest userDetails) {
        
        if(playerService.usernameTaken(userDetails.getUsername()))
            return ResponseEntity.badRequest().body(new AuthResponse("Username is already taken, please try another one"));

        if(playerService.savePlayer(userDetails))
            return ResponseEntity.ok(new AuthResponse("User saved!"));
  
    }
}
