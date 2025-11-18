package com.project.eventos.dtos.user;


import java.time.LocalDate;
import java.util.Set;

import com.project.eventos.enums.user.Role;

public class UserDTO {

    private Long id;
    private String nome;
    private String email;
    private String cidade;
    private LocalDate nascimento;
    private String avatarUrl;
    private Set<Role> roles;

    // O campo 'senhaHash' da entidade Usuario é escondido por questões de segurança...

    public UserDTO() {
    }

    public UserDTO(Long id, String nome, String email, String cidade, LocalDate nascimento, String avatarUrl, Set<Role> roles) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.nascimento = nascimento;
        this.avatarUrl = avatarUrl;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCidade() {
        return cidade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Set<Role> getRoles() {
        return roles;
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
        UserDTO other = (UserDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public void setId(Long id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    public void setNome(String nome2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'");
    }

    public void setEmail(String email2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }

    public void setCidade(String cidade2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCidade'");
    }

    public void setNascimento(LocalDate nascimento2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNascimento'");
    }

    public void setAvatarUrl(String avatarUrl2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAvatarUrl'");
    }

    public void setRoles(Set<Role> roles2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRoles'");
    }

    
}