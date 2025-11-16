package com.project.eventos.dtos.user;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

import com.project.eventos.enums.user.Role;

@Data
public class UserDTO {

    private Long id;
    private String nome;
    private String email;
    private String cidade;
    private LocalDate nascimento;
    private String avatarUrl;
    private Set<Role> roles;

    // O campo 'senhaHash' da entidade Usuario é escondido por questões de segurança...
}