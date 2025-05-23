package br.com.saudeemacao.Saude.em.acao.controller;

import br.com.saudeemacao.Saude.em.acao.security.JwtTokenProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Getter
@Setter
@RestController
public class AuthController {

    private long jwtExpiration;

    @GetMapping("/teste-token")
    public String testeToken() {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpiration);

        return "Agora: " + now + "\nExpira: " + expiry;
    }


    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // Aqui é necessário validar o username e password no banco
        // Se estiverem corretos:


        return tokenProvider.generateToken(request.getUsername(), tokenProvider);
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
