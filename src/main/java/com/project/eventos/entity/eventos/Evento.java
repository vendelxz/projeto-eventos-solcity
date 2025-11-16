package com.project.eventos.entity.eventos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.eventos.entity.organizadora.Organizadora;
import com.project.eventos.enums.evento.EventStatus;
import com.project.eventos.enums.evento.EventType;
import com.project.eventos.enums.evento.Modality;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizadora_id", nullable = false)
    private Organizadora organizadora;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType tipo; // Ex: CORRIDA, CICLISMO, CAMPEONATO

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modality modalidade; // Ex: CORRIDA_RUA, MTB, FUTEBOL_CAMPO

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, length = 120)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(name = "venue") // Local do evento
    private String local;

    @Column(precision = 10, scale = 7)
    private BigDecimal lat;

    @Column(precision = 10, scale = 7)
    private BigDecimal lng;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Enumerated(EnumType.STRING)
    private EventStatus status; // Ex: RASCUNHO, PUBLICADO, CANCELADO

    private Integer capacity; // Capacidade máxima de participantes

    private LocalDateTime registrationOpen;
    private LocalDateTime registrationClose;

    @Column(name = "cover_url")
    private String coverUrl;
}
