package io.ronakdave.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.ronakdave.game.model.Player;
import io.ronakdave.game.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
