package com.example.kafkawebsocketchatapp.controller;

import com.example.kafkawebsocketchatapp.kafka.producer.MessageProducer;
import com.example.kafkawebsocketchatapp.model.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageProducer messageProducer;
    private final SimpMessageSendingOperations messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        message.setSessionId(headerAccessor.getSessionId());
        messageProducer.sendMessage("message-topic", message);
        logger.info("Sending message to /topic/public: " + message);
        messagingTemplate.convertAndSend("/topic/public", message);
        logger.info("Message sent to /topic/public: " + message);
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("username", message.getSender());
        }
        return message;
    }
}
