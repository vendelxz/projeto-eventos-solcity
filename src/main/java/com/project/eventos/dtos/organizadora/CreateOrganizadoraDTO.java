package com.project.eventos.dtos.organizadora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CreateOrganizadoraDTO {

    // O ID do usuário dono será pego do token de autenticação (JWT)
   

    @NotBlank(message = "O nome de exibição é obrigatório.")
    @Size(min = 3, max = 150)
    private String displayName;

    @NotBlank(message = "O contato é obrigatório.")
    private String contato;

    private String address;

    public CreateOrganizadoraDTO() {
    }

    public CreateOrganizadoraDTO(String displayName, String contato, String address) {
        this.displayName = displayName;
        this.contato = contato;
        this.address = address;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getContato() {
        return contato;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((contato == null) ? 0 : contato.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
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
        CreateOrganizadoraDTO other = (CreateOrganizadoraDTO) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (contato == null) {
            if (other.contato != null)
                return false;
        } else if (!contato.equals(other.contato))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        return true;
    }

    

}
