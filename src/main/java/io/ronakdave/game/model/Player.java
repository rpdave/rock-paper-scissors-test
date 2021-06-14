package io.ronakdave.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "players")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String roles;
    
    // Player Stats
    @JsonIgnore
    private int gamesPlayed;
    @JsonIgnore
    private int gamesWon;
    @JsonIgnore
    private int gamesLost;
    @JsonIgnore
    private int gamesDraw;
}
