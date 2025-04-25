package br.com.saudeemacao.Saude.em.acao.config;

import br.com.saudeemacao.Saude.em.acao.security.JwtTokenProvider;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
public class AuthHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthHandshakeInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

            // 1. Extrai o token da query string
            String token = servletRequest.getServletRequest().getParameter("token");

            // 2. Validação JWT
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 3. Adiciona o username aos atributos da sessão WebSocket
                String username = jwtTokenProvider.getUsernameFromToken(token);
                attributes.put("username", username);
                return true;
            }
        }

        // 4. Rejeita conexões sem token válido
        return false;
    }
}
