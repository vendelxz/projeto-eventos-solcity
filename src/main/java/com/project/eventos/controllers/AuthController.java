package com.project.eventos.controllers;

import com.project.eventos.dtos.AuthResponseDTO;
import com.project.eventos.dtos.LoginUserDTO;
import com.project.eventos.dtos.RegisterUserDTO;
import com.project.eventos.dtos.UserDTO;
import com.project.eventos.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") 
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para registro de novos usuários.
     * Mapeado para POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterUserDTO registerDTO) {
        UserDTO usuario = authService.register(registerDTO);
        
        // Retorna 201 Created com o usuário criado (sem a senha)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    /**
     * Endpoint para login de usuários existentes.
     * Mapeado para POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginUserDTO loginDTO) {
        AuthResponseDTO authResponse = authService.login(loginDTO);
        
        // Retorna 200 OK com o token e os dados do usuário
        return ResponseEntity.ok(authResponse);
    }
}