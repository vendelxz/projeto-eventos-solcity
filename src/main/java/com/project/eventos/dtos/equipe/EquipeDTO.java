package com.project.eventos.dtos.equipe;


public class EquipeDTO {
    private Long id;
    private String nome;
    private String cidade;
    private String logoUrl;
    private Long managerUserId; 
    private String managerName; 

    public EquipeDTO() {
    }

    public EquipeDTO(Long id, String nome, String cidade, String logoUrl, Long managerUserId, String managerName) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.logoUrl = logoUrl;
        this.managerUserId = managerUserId;
        this.managerName = managerName;
    }

    public Long getId() {
        return id;
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

    public Long getManagerUserId() {
        return managerUserId;
    }

    public String getManagerName() {
        return managerName;
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
        EquipeDTO other = (EquipeDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}