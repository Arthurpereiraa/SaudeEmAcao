package br.com.saudeemacao.Saude.em.acao.handler;

import br.com.saudeemacao.Saude.em.acao.model.Mensagem;
import br.com.saudeemacao.Saude.em.acao.repository.MensagemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final MensagemRepository messageRepository;

    @Autowired
    public ChatWebSocketHandler(MensagemRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            // Recebe a mensagem, converte para o objeto Message e salva no MySQL
            Mensagem newMessage = objectMapper.readValue(message.getPayload(), Mensagem.class);
            messageRepository.save(newMessage); // Salva a mensagem no banco

            // Extrai o chatId da URL da sessão (parâmetro de consulta)
            String sessionChatId = extractChatIdFromSession(session);

            // Envia a mensagem apenas para os usuários conectados ao mesmo chatId
            for (WebSocketSession webSocketSession : sessions) {
                String webSocketChatId = extractChatIdFromSession(webSocketSession); // Extrai o chatId da outra sessão
                if (webSocketSession.isOpen() && sessionChatId != null && sessionChatId.equals(webSocketChatId)) {
                    webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(newMessage)));
                }
            }
        } catch (IOException e) {
            // Logar o erro
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session); // Remove a sessão quando o WebSocket é desconectado
    }

    // Método auxiliar para extrair o chatId da URL
    private String extractChatIdFromSession(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query != null && query.contains("chatId=")) {
            return query.substring(query.indexOf("chatId=") + 7);
        }
        return null; // Ou lance uma exceção, dependendo da sua lógica
    }
}
