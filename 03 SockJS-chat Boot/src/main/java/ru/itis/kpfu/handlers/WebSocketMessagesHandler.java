package ru.itis.kpfu.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.kpfu.dto.MessageDto;
import ru.itis.kpfu.services.interfaces.JsonProcessService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@EnableWebSocket
@AllArgsConstructor
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final JsonProcessService jsonProcessService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {

        String messageText = (String) message.getPayload();
        var messageFromJson = jsonProcessService.parse(messageText);

        if (!sessions.containsKey(Objects.requireNonNull(messageFromJson).getFrom())) {
            sessions.put(messageFromJson.getFrom().getName(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            try {
                currentSession.sendMessage(message);
            } catch (IOException e) {
                System.out.println("Can't send message to session: " + session.getUri() + "\n" + e.getMessage());
            }
        }
    }

}
