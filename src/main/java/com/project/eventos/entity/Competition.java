package com.project.eventos.entity; 

import com.project.eventos.enums.CompetitionFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ligação um-para-um: Um Evento (do tipo campeonato) 
    // tem uma definição de Competição.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetitionFormat format; // Ex: LEAGUE, KNOCKOUT, GROUP_STAGE

    @Column(name = "groups_count") // 'groups' pode ser uma palavra reservada em SQL
    private Integer groups;

    private Integer rounds;

    private Boolean doubleRound; // Indica se é ida e volta
}