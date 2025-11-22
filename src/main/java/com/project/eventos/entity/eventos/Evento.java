package com.project.eventos.entity.eventos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.eventos.entity.organizadora.Organizadora;
import com.project.eventos.enums.evento.EventStatus;
import com.project.eventos.enums.evento.EventType;
import com.project.eventos.enums.evento.Modality;


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

    public Evento() {
    }

    public Evento(Organizadora organizadora, EventType tipo, Modality modalidade, String titulo, String descricao, String cidade, String estado, String local, BigDecimal lat, BigDecimal lng, LocalDateTime startDateTime, EventStatus status, Integer capacity, LocalDateTime registrationOpen, LocalDateTime registrationClose, String coverUrl) {
        this.organizadora = organizadora;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.titulo = titulo;
        this.descricao = descricao;
        this.cidade = cidade;
        this.estado = estado;
        this.local = local;
        this.lat = lat;
        this.lng = lng;
        this.startDateTime = startDateTime;
        this.status = status;
        this.capacity = capacity;
        this.registrationOpen = registrationOpen;
        this.registrationClose = registrationClose;
        this.coverUrl = coverUrl;
    }

    public Long getId() {
        return id;
    }

    public Organizadora getOrganizadora() {
        return organizadora;
    }

    public EventType getTipo() {
        return tipo;
    }

    public Modality getModalidade() {
        return modalidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getLocal() {
        return local;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public LocalDateTime getRegistrationOpen() {
        return registrationOpen;
    }

    public LocalDateTime getRegistrationClose() {
        return registrationClose;
    }

    public String getCoverUrl() {
        return coverUrl;
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
        Evento other = (Evento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    
}
