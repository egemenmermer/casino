package com.ego.casino.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
@Entity
@Table(name = "game_history")
public class GameHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private Timestamp playDate;

    @Column(nullable = false)
    private BigDecimal betAmount;

    @Column(nullable = false)
    private BigDecimal oldBalance;

    @Column(nullable = false)
    private BigDecimal newBalance;
}
