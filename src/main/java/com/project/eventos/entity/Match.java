package com.project.eventos.entity; 

import com.project.eventos.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitas partidas para uma Competição
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    private Integer round; // Rodada

    @Column(name = "group_name") // "group" é palavra reservada em SQL
    private String group; // Grupo (ex: "A", "B")

    // Muitas partidas podem ter a mesma equipe como time da casa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private Equipe homeTeam;

    // Muitas partidas podem ter a mesma equipe como time visitante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private Equipe awayTeam;

    private LocalDateTime startAt; // Data e hora da partida

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status; // Ex: AGENDADA, FINALIZADA

    private Integer scoreHome; // Placar time da casa
    private Integer scoreAway; // Placar time visitante
}