package com.project.eventos.dtos.organizadora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrganizadoraDTO {

    // O ID do usuário dono será pego do token de autenticação (JWT)
   

    @NotNull(message = "Nome de exibição não pode ser nulo.")
    @NotBlank(message = "O nome de exibição é obrigatório.")
    @Size(min = 3, max = 150)
    private String displayName;

    @NotBlank(message = "O contato é obrigatório.")
    private String contato;

    private String address;
}
