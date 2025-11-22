package com.project.eventos.entity.user;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.eventos.enums.user.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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

    public Usuario() {
    }

    public Usuario(String nome, String email, String senhaHash, String cidade, LocalDate nascimento, String avatarUrl, Set<Role> roles) {
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.cidade = cidade;
        this.nascimento = nascimento;
        this.avatarUrl = avatarUrl;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public String getCidade() {
        return cidade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Set<Role> getRoles() {
        return roles;
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public void setNome(String nome2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'");
    }

    public void setEmail(String email2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }

    public void setSenhaHash(String encode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSenhaHash'");
    }

    public void setCidade(String cidade2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCidade'");
    }

    public void setNascimento(LocalDate nascimento2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNascimento'");
    }

    public void setRoles(Set<Role> of) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRoles'");
    }

    

}
