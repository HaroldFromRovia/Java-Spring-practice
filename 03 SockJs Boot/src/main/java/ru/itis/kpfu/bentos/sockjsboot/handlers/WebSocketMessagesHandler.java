package ru.itis.kpfu.bentos.sockjsboot.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.kpfu.bentos.sockjsboot.dto.Message;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    private final ObjectMapper objectMapper;

    public WebSocketMessagesHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageText = (String) message.getPayload();
        Message messageFromJson = objectMapper.readValue(messageText, Message.class);

        if (!sessions.containsKey(messageFromJson.getFrom())) {
            sessions.put(messageFromJson.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            currentSession.sendMessage(message);
        }
    }

}
