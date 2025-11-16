package com.project.eventos.entity.organizadora;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.eventos.entity.user.Usuario;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Usuario owner; // Link para a entidade Usuário

    @Column(name = "display_name", nullable = false, length = 150)
    private String displayName; // Nome público da Organizadora

    @Column(nullable = false)
    private Boolean verified = false; // Começa como não verificada

    @Column(length = 100)
    private String contato; // Pode ser um e-mail ou telefone de contato público

    private String address; // Endereço

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}