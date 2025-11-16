package com.project.eventos.entity.partidas;

import com.project.eventos.entity.equipe.Equipe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}