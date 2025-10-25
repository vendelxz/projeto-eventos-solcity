package com.project.eventos.dtos;

import lombok.Data;

@Data
public class EquipeDTO {
    private Long id;
    private String nome;
    private String cidade;
    private String logoUrl;
    private Long managerUserId; // Apenas o ID do gerente
    private String managerName; // Útil para exibir o nome do gerente
}