package com.project.eventos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token; // O Token JWT
    private UserDTO user;  // Os dados do usuário (seguros, do UserDTO)

}