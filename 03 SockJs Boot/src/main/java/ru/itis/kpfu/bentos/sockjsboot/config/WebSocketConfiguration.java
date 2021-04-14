package ru.itis.kpfu.bentos.sockjsboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.kpfu.bentos.sockjsboot.handlers.AuthHandshakeHandler;
import ru.itis.kpfu.bentos.sockjsboot.handlers.WebSocketMessagesHandler;

@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final WebSocketMessagesHandler handler;
    private final AuthHandshakeHandler handshakeHandler;

    public WebSocketConfiguration(WebSocketMessagesHandler handler, AuthHandshakeHandler handshakeHandler) {
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
