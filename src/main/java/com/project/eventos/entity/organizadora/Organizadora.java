package com.project.eventos.entity.organizadora;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.eventos.entity.user.Usuario;

import java.time.LocalDateTime;


@Entity
@Table(name = "organizadoras")
@EntityListeners(AuditingEntityListener.class) // Habilita auditoria de datas
public class Organizadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um Usuário é dono de um perfil de Organizadora
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_usuario_id", nullable = false, unique = true)
    private Usuario owner; 

    @Column(name = "display_name", nullable = false, length = 150)
    private String displayName; // Nome público da Organizadora

    @Column(nullable = false)
    private Boolean verified = false; // Começa como não verificada

    @Column(length = 100)
    private String contato; // Pode ser um e-mail ou telefone de contato público

    private String address; 

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Organizadora() {
    }

    public Organizadora(Usuario owner, String displayName, String contato, String address) {
        this.owner = owner;
        this.displayName = displayName;
        this.contato = contato;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Usuario getOwner() {
        return owner;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getContato() {
        return contato;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
        Organizadora other = (Organizadora) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}