package br.com.saudeemacao.Saude.em.acao.config;

import br.com.saudeemacao.Saude.em.acao.handler.ChatWebSocketHandler;
import br.com.saudeemacao.Saude.em.acao.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/chat")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public ChatWebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler(mensagemRepository);
    }

    // Adicione esta parte apenas se não estiver usando injeção de construtor
    @Autowired
    private MensagemRepository mensagemRepository;
}
