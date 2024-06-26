package com.example.kafkawebsocketchatapp.controller;


import com.example.kafkawebsocketchatapp.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message) {
        kafkaTemplate.send("message-topic", message);
        messagingTemplate.convertAndSend("/topic/public", message);
        log.info("Message send from Kafka {}", message);
        return "Message sent!";
    }
}
