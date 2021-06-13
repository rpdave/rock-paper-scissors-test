package io.ronakdave.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ronakdave.game.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    boolean existsByUsername(String username);
    Optional<Player> findByUsername(String username);
}
