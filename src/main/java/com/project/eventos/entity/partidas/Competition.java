package com.project.eventos.entity.partidas; 

import com.project.eventos.entity.eventos.Evento;
import com.project.eventos.enums.partida.CompetitionFormat;

import jakarta.persistence.*;


@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ligação um-para-um: Um Evento (do tipo campeonato) 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetitionFormat format; // Ex: LEAGUE, KNOCKOUT, GROUP_STAGE

    @Column(name = "groups_count") 
    private Integer groups;

    private Integer rounds;

    private Boolean doubleRound; // Indica se é ida e volta


    public Competition() {
    }

    public Competition(Evento evento, CompetitionFormat format, Integer groups, Integer rounds, Boolean doubleRound) {
        this.evento = evento;
        this.format = format;
        this.groups = groups;
        this.rounds = rounds;
        this.doubleRound = doubleRound;
    }

    public Long getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public CompetitionFormat getFormat() {
        return format;
    }

    public Integer getGroups() {
        return groups;
    }

    public Integer getRounds() {
        return rounds;
    }

    public Boolean getDoubleRound() {
        return doubleRound;
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
        Competition other = (Competition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}