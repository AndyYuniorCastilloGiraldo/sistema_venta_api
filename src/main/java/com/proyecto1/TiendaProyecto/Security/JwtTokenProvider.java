package com.proyecto1.TiendaProyecto.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;                    
import org.slf4j.LoggerFactory;             
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.proyecto1.TiendaProyecto.Model.Usuario;
import com.proyecto1.TiendaProyecto.Model.Role;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(usuario.getUsername())
                .claim("roles", usuario.getRoles().stream().map(Role::getNombre).toList())
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(getSigningKey())
                .compact();
    }

    public String obtenerUsernameDelToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) 
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validarToken(String token) {
         try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token inválido: {}", e.getMessage());
            return false;
        }
    }
}