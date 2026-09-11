package com.project.eventos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.eventos.entity.user.Usuario;

@RestController
@RequestMapping("/test") 
public class TestController {

    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData(
        @AuthenticationPrincipal Usuario usuario) {
        
        // O Spring Security pega o usuário que nosso JwtAuthFilter
        // injetou no contexto e o coloca nesta variável 'usuario'.

        if (usuario != null) {
            return ResponseEntity.ok("Olá, " + usuario.getNome() + 
                "! Seu ID é " + usuario.getId() + ". Você está autenticado!");
        }

        return ResponseEntity.status(401).body("Usuário não autenticado.");
    }
}