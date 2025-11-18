package com.project.eventos.dtos.user;


public class AuthResponseDTO {

    private String token; // O Token JWT
    private UserDTO user;  // Os dados do usuário (seguros, do UserDTO)

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }


}