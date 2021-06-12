package io.ronakdave.game.service;

import org.springframework.stereotype.Service;

import io.ronakdave.game.model.Player;
import io.ronakdave.game.repository.PlayerRepository;
import io.ronakdave.game.request.UserAuthRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void savePlayer(UserAuthRequest userDetails) {

        Player newPlayer = Player.builder()
            .username(userDetails.getUsername())
            // Encode this password
            .password(userDetails.getPassword())
            .build();
        
        playerRepository.save(newPlayer);

    }

    public boolean usernameTaken(String username) {
        return playerRepository.existsByUsername(username);
    }
}
