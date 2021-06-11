package io.ronakdave.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    // Player Stats
    private Integer totalPlayed;
    private Integer won;
    private Integer lost;
    private Double winPercent;
    private Double lossPercent;
}
