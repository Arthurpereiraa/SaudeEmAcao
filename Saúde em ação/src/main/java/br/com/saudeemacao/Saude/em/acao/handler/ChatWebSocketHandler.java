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
            // Converte a mensagem JSON para objeto Mensagem
            Mensagem newMessage = objectMapper.readValue(message.getPayload(), Mensagem.class);

            // Salva a mensagem no banco de dados
            messageRepository.save(newMessage);

            // Extrai o chatId da sessão atual
            String sessionChatId = extractChatIdFromSession(session);

            // Envia a mensagem para todos no mesmo chatId
            for (WebSocketSession webSocketSession : sessions) {
                String webSocketChatId = extractChatIdFromSession(webSocketSession);
                if (webSocketSession.isOpen() && sessionChatId != null && sessionChatId.equals(webSocketChatId)) {
                    webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(newMessage)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    private String extractChatIdFromSession(WebSocketSession session) {
        try {
            String query = session.getUri().getQuery();
            if (query != null) {
                String[] params = query.split("&");
                for (String param : params) {
                    if (param.startsWith("chatId=")) {
                        return param.substring(7);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
