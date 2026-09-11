package com.project.eventos.security;

import com.project.eventos.entity.user.Usuario;
import com.project.eventos.repository.UserRepository;
import com.project.eventos.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public JwtAuthFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final Long userId;

        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Se não continua o fluxo
            return;
        }

        jwt = authHeader.substring(7); // Extrai o token (remove "Bearer ")

        
        if (!tokenService.validateToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        userId = tokenService.getUserIdFromToken(jwt);

    
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
          
            // NOTA: Para um sistema mais robusto, criar uma classe UserDetailsImpl
            // e um serviço UserDetailsService. Por agora, isso funciona.
            
            Usuario usuario = this.userRepository.findById(userId)
                    .orElse(null); // O usuário pode ter sido deletado

            if (usuario != null) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario, // O principal (usuário)
                        null,    // Credenciais (não necessárias para JWT)
                        null     // Autoridades/Roles (você pode carregar do tokenService.getRolesFromToken())
                );
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // Continua o fluxo para o Controller
    }
}