package br.com.saudeemacao.Saude.em.acao.controller;

import br.com.saudeemacao.Saude.em.acao.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // Aqui é necessário validar o username e password no banco
        // Se estiverem corretos:
        return tokenProvider.generateToken(request.getUsername());
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
