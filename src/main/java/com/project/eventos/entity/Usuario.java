package com.project.eventos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.eventos.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data // Anotação do Lombok para gerar Getters, Setters, toString, etc.
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Entity // Marca esta classe como uma entidade JPA
@Table(name = "users") // Mapeia para a tabela 'users' no banco
@EntityListeners(AuditingEntityListener.class) // Habilita a auditoria de datas (createdAt, updatedAt)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String senhaHash;

    @Column(length = 120)
    private String cidade;

    @Column(name = "birth_date")
    private LocalDate nascimento;

    @Column(name = "avatar_url")
    private String avatarUrl;

    // Usando ElementCollection para mapear um conjunto de Enums para uma tabela separada
    // ou uma coluna, dependendo da estratégia. É mais flexível que um simples Varchar.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
