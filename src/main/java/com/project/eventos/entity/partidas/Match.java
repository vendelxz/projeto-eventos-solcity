package com.project.eventos.entity.partidas; 

import com.project.eventos.entity.equipe.Equipe;
import com.project.eventos.enums.partida.MatchStatus;

import jakarta.persistence.*;


import java.time.LocalDateTime;


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

    @Column(name = "group_name") 
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

    public Match() {
    }

    public Match(Competition competition, Integer round, String group, Equipe homeTeam, Equipe awayTeam, LocalDateTime startAt, MatchStatus status) {
        this.competition = competition;
        this.round = round;
        this.group = group;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startAt = startAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Integer getRound() {
        return round;
    }

    public String getGroup() {
        return group;
    }

    public Equipe getHomeTeam() {
        return homeTeam;
    }

    public Equipe getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public Integer getScoreHome() {
        return scoreHome;
    }

    public Integer getScoreAway() {
        return scoreAway;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Match other = (Match) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}