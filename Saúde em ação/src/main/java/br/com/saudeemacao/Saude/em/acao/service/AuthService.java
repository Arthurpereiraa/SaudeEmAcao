package br.com.saudeemacao.Saude.em.acao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import br.com.saudeemacao.Saude.em.acao.dto.LoginDTO;
import br.com.saudeemacao.Saude.em.acao.model.Usuario;
import br.com.saudeemacao.Saude.em.acao.repository.UsuarioRepository;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String SECRET_KEY = "secret";

    public String login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return gerarToken(usuario);
    }

    private String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
