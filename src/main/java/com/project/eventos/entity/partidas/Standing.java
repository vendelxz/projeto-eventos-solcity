package com.project.eventos.entity.partidas;

import com.project.eventos.entity.equipe.Equipe;

import jakarta.persistence.*;



@Entity
@Table(name = "standings",
    // Garante que uma equipa não possa ter duas entradas na mesma competição
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"competition_id", "team_id"})
    }
)
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitas linhas de classificação para uma Competição
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    // Muitas linhas de classificação podem pertencer a uma Equipa (em diferentes competições)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Equipe equipe;

    // Colocamos 0 para garantir que 
    // os valores nunca sejam nulos no banco.
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer played = 0; // Jogos

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer wins = 0; // Vitórias

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer draws = 0; // Empates

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer losses = 0; // Derrotas

    @Column(name = "goals_for", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer goalsFor = 0; // Gols Feitos (gf)

    @Column(name = "goals_against", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer goalsAgainst = 0; // Gols Contra (ga)

    @Column(name = "goal_difference", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer goalDifference = 0; // Saldo de Gols (gd)

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer points = 0; // Pontos


    public Standing() {
    }

    public Standing(Competition competition, Equipe equipe) {
        this.competition = competition;
        this.equipe = equipe;
    }

    public Long getId() {
        return id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Integer getPlayed() {
        return played;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public Integer getPoints() {
        return points;
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
        Standing other = (Standing) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}