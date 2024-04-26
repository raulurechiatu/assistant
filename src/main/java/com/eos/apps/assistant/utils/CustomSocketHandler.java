package com.eos.apps.assistant.utils;

import com.eos.apps.assistant.objects.MessageType;
import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.objects.SocketMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CustomSocketHandler implements WebSocketHandler {
    private static WebSocketSession session;
    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established on session: {}", session.getId());
        this.session = session;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String tutorial = (String) message.getPayload();
        log.info("Message: {}", tutorial);
        session.sendMessage(new TextMessage("Started processing tutorial: " + session + " - " + tutorial));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Completed processing tutorial: " + tutorial));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception occured: {} on session: {}", exception.getMessage(), session.getId());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getCode());

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

//    @Scheduled(fixedRate = 5000)
//    void sendMessage() throws IOException {
//        if (session == null) return;
//        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        session.sendMessage(new TextMessage("time: " + time));
//    }

    public static void sendItem(Object item, MessageType messageType) throws IOException {
        if (session == null) return;
        SocketMessage socketMessage = new SocketMessage(item, messageType);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(socketMessage)));
    }
}