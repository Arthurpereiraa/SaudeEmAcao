package br.com.saudeemacao.Saude.em.acao.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Setter
@Getter
@Component
public class JwtTokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;
    private long jwtExpiration;

    // Adicione este método
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);

        // Garante que a chave tenha 64 bytes (512 bits)
        if (keyBytes.length < 64) {
            throw new IllegalArgumentException("Chave JWT deve ter 64 bytes (512 bits)");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, JwtTokenProvider tokenProvider) {
        Date now = new Date();

        // Validação extra para segurança
        if (jwtExpiration <= 0) {
            throw new IllegalArgumentException("Expiração deve ser positiva");
        }

        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }



    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Modificado
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Modificado
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
