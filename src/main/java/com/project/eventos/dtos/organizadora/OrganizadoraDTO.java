package com.project.eventos.dtos.organizadora;

import lombok.Data;

@Data
public class OrganizadoraDTO {
    private Long id;
    private Long ownerUsuarioId; // Retornamos apenas o ID do dono
    private String displayName;
    private Boolean verified;
    private String contato;
    private String address;
}
