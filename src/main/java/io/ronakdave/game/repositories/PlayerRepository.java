package io.ronakdave.game.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ronakdave.game.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
