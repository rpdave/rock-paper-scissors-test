package io.ronakdave.game.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.ronakdave.game.model.GameUserDetails;
import io.ronakdave.game.model.Player;
import io.ronakdave.game.repository.PlayerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PlayerRepository playerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> player = playerRepository.findByUsername(username);

        return new GameUserDetails(player.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }
    
}
