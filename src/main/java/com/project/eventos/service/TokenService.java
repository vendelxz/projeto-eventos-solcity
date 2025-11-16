package com.project.eventos.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.eventos.entity.user.Usuario;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    public String generateToken(Usuario usuario) {
        // "Claims" são as informações que colocamos dentro do token
        Map<String, Object> claims = new HashMap<>();
        
        // Colocamos os papéis (roles) para futura verificação de permissão
        claims.put("roles", usuario.getRoles()); 

        return Jwts.builder()
                .claims(claims) 
                .subject(usuario.getId().toString()) 
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser() // Método moderno (corrigindo o 'parserBuilder()')
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token); // Renomeado de 'parseClaimsJws'
            return true;
        } catch (Exception e) {
            // Token inválido (expirado, assinatura errada, etc.)
            return false;
        }
    }

    /**
     * Extrai o ID do usuário (o "Subject") de dentro do token.
     */
    public Long getUserIdFromToken(String token) {
        String subject = getClaimFromToken(token, Claims::getSubject);
        return Long.parseLong(subject);
    }
    
    /**
     * Extrai os papéis (roles) de dentro do token.
     */
    public Object getRolesFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("roles"));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload(); // Renomeado de 'getBody'
        return claimsResolver.apply(claims);
    }

    /**
     * Cria a chave de assinatura (SecretKey) a partir da string secreta.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}