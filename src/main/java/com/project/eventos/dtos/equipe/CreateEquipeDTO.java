package com.project.eventos.dtos.equipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEquipeDTO {

    // O managerID virá do token que será processado no service!!
   

    @NotBlank(message = "O nome da equipe é obrigatório.")
    @Size(min = 3, max = 150)
    private String nome;

    private String cidade;

    private String logoUrl;

    public CreateEquipeDTO() {
    }

    public CreateEquipeDTO(String nome, String cidade, String logoUrl) {
        this.nome = nome;
        this.cidade = cidade;
        this.logoUrl = logoUrl;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((logoUrl == null) ? 0 : logoUrl.hashCode());
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
        CreateEquipeDTO other = (CreateEquipeDTO) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (logoUrl == null) {
            if (other.logoUrl != null)
                return false;
        } else if (!logoUrl.equals(other.logoUrl))
            return false;
        return true;
    }

    
}