package com.project.eventos.entity.eventos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


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


    public EventLot() {
    }

    public EventLot(Evento evento, String name, BigDecimal price, LocalDateTime startAt, LocalDateTime endAt, Integer quantity) {
        this.evento = evento;
        this.name = name;
        this.price = price;
        this.startAt = startAt;
        this.endAt = endAt;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSold() {
        return sold;
    }

    public Integer getVersion() {
        return version;
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
        EventLot other = (EventLot) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
