package com.project.eventos.dtos.equipe;

import lombok.Data;

@Data
public class EquipeDTO {
    private Long id;
    private String nome;
    private String cidade;
    private String logoUrl;
    private Long managerUserId; 
    private String managerName; 
}