package com.project.eventos.entity.eventos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_lots")
public class EventLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos lotes para um evento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String name; // Ex: "Lote 1", "Lote Promocional"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private Integer quantity; // Quantidade total do lote
    private Integer sold = 0; // Quantidade vendida, inicializa com 0

    @Version // Para controle de concorrência (Optimistic Locking)
    private Integer version;
}
