package com.project.eventos.dtos.equipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateEquipeDTO {

    // Assumimos que o 'managerId' virá do usuário autenticado (token)
    // e será processado no Service.

    @NotBlank(message = "O nome da equipe é obrigatório.")
    @Size(min = 3, max = 150)
    private String nome;

    private String cidade;

    private String logoUrl;
}