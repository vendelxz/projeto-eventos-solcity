package com.project.eventos.mappers;

import com.project.eventos.dtos.UserDTO;
import com.project.eventos.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Converte uma entidade Usuario para um UserDTO (seguro para resposta).
     */
    public UserDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCidade(usuario.getCidade());
        dto.setNascimento(usuario.getNascimento());
        dto.setAvatarUrl(usuario.getAvatarUrl());
        dto.setRoles(usuario.getRoles());
        
        // Propositalmente omitindo o senhaHash
        
        return dto;
    }
}