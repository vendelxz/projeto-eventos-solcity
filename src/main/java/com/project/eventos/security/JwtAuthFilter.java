package com.project.eventos.security;

import com.project.eventos.entity.Usuario;
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

        // 1. Verifica se o cabeçalho 'Authorization' existe e começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Se não, apenas continua o fluxo
            return;
        }

        jwt = authHeader.substring(7); // Extrai o token (remove "Bearer ")

        // 2. Valida o token e extrai o ID do usuário
        if (!tokenService.validateToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        userId = tokenService.getUserIdFromToken(jwt);

        // 3. Se o token é válido, carrega o usuário do banco
        // (Verifica também se o usuário já não está autenticado nesta requisição)
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
          
            // NOTA: Para um sistema mais robusto, criar uma classe UserDetailsImpl
            // e um serviço UserDetailsService. Por agora, isso funciona.
            
            Usuario usuario = this.userRepository.findById(userId)
                    .orElse(null); // O usuário pode ter sido deletado

            if (usuario != null) {
                // 4. Cria o "Token de Autenticação" do Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario, // O principal (usuário)
                        null,    // Credenciais (não necessárias para JWT)
                        null     // Autoridades/Roles (você pode carregar do tokenService.getRolesFromToken())
                );
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 5. Coloca o usuário no Contexto de Segurança.
                // A partir daqui, o Spring sabe que esta requisição está autenticada.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // Continua o fluxo para o Controller
    }
}