package com.project.eventos.dtos;

import com.project.eventos.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String nome;
    private String email;
    private String cidade;
    private LocalDate nascimento;
    private String avatarUrl;
    private Set<Role> roles;

    // Construtor, Getters e Setters são gerenciados pelo @Data (Lombok)
    // O campo 'senhaHash' da entidade Usuario é omitido propositalmente.
}