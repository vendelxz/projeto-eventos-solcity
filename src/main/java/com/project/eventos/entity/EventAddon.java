package com.project.eventos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String name; // Ex: "Camiseta Extra", "Jantar de Massas"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Integer stock; // Estoque disponível
}
