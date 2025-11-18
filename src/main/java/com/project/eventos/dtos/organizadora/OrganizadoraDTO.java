package com.project.eventos.dtos.organizadora;


public class OrganizadoraDTO {
    private Long id;
    private Long ownerUsuarioId; 
    private String displayName;
    private Boolean verified;
    private String contato;
    private String address;

    public OrganizadoraDTO() {
    }

    public OrganizadoraDTO(Long id, Long ownerUsuarioId, String displayName, Boolean verified, String contato, String address) {
        this.id = id;
        this.ownerUsuarioId = ownerUsuarioId;
        this.displayName = displayName;
        this.verified = verified;
        this.contato = contato;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Long getOwnerUsuarioId() {
        return ownerUsuarioId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean getVerified() {
        return verified;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        OrganizadoraDTO other = (OrganizadoraDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   

    
}
