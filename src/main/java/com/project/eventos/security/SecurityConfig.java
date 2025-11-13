package com.project.eventos.security;

import com.project.eventos.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração central de segurança para a aplicação.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * O Bean que já tínhamos: o codificador de senhas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * O BEAN PRINCIPAL: O 'SecurityFilterChain'.
     * Aqui definimos as regras da nossa API.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Desabilita CSRF (Cross-Site Request Forgery), 
            //    pois não usamos sessões/cookies.
            .csrf(AbstractHttpConfigurer::disable)

            // 2. Define as regras de autorização
            .authorizeHttpRequests(authorize -> authorize
                // Endpoints públicos (Registro e Login)
                .requestMatchers("/auth/**").permitAll() 
                
                // Todos os outros endpoints exigem autenticação
                .anyRequest().authenticated()
            )

            // 3. Define a política de sessão como STATELESS (sem estado)
            //    O servidor não guardará nenhuma sessão de usuário.
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 4. Adiciona nosso Filtro JWT
            //    Ele rodará ANTES do filtro padrão de username/password
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // NOTA: Para o AuthService.login() funcionar como está (checando a senha
    // manualmente), não precisamos de AuthenticationManager/Provider aqui.
    // Se fôssemos usar o login do Spring, precisaríamos deles.
    // Sua implementação do AuthService está perfeita para este fluxo.
}