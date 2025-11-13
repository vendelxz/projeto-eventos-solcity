package com.project.eventos.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.eventos.enums.RegistrationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}