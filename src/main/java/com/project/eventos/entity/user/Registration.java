package com.project.eventos.entity.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.eventos.entity.eventos.Evento;
import com.project.eventos.enums.user.RegistrationStatus;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Evento (substitui eventId)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Evento event;

    // IDs simples (relacionamentos podem ser mapeados se houver entidades User/Team/Lot)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "total_amount", precision = 13, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus status;

    // Armazena respostas do formulário; usa conversor para persistir como JSON/text
   // @Convert(converter = com.project.eventos.persistence.JsonNodeConverter.class)
    //@Column(columnDefinition = "jsonb")
    //private JsonNode answers;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

    public Registration() {
    }

    public Registration(Evento event, Long userId, Long teamId, Long lotId, BigDecimal totalAmount, RegistrationStatus status) {
        this.event = event;
        this.userId = userId;
        this.teamId = teamId;
        this.lotId = lotId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Evento getEvent() {
        return event;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getLotId() {
        return lotId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
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
        Registration other = (Registration) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}