package ru.itis.kpfu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.kpfu.handlers.AuthHandshakeHandler;
import ru.itis.kpfu.handlers.WebSocketMessagesHandler;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketMessagesHandler handler;
    private final AuthHandshakeHandler handshakeHandler;

    public WebSocketConfig(WebSocketMessagesHandler handler, AuthHandshakeHandler handshakeHandler) {
        this.handler = handler;
        this.handshakeHandler = handshakeHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/chat")
                .setHandshakeHandler(handshakeHandler)
                .withSockJS();
    }
}
