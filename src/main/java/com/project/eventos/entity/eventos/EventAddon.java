package com.project.eventos.entity.eventos;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "event_addons")
public class EventAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos adicionais para um evento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String name; // Ex: "Camiseta Extra"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Integer stock; // Estoque disponível

    public EventAddon() {
    }

    public EventAddon(Evento evento, String name, BigDecimal price, Integer stock) {
        this.evento = evento;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public Integer getStock() {
        return stock;
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
        EventAddon other = (EventAddon) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
