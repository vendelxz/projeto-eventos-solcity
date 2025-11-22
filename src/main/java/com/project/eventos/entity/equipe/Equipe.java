package com.project.eventos.entity.equipe; 

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.eventos.entity.user.Usuario;

import java.time.LocalDateTime;

@Entity
@Table(name = "teams")
@EntityListeners(AuditingEntityListener.class) 
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 120)
    private String cidade;

    @Column(name = "logo_url")
    private String logoUrl;

    // Muitas equipes podem ser gerenciadas por um usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_user_id", nullable = false)
    private Usuario manager; 

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // TODO: Adicionar futuramente o relacionamento @OneToMany para TeamMember (membros da equipe)

    public Equipe() {
    }

    public Equipe(String nome, String cidade, String logoUrl, Usuario manager) {
        this.nome = nome;
        this.cidade = cidade;
        this.logoUrl = logoUrl;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Usuario getManager() {
        return manager;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    

}