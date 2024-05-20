package com.example.kafkawebsocketchatapp.kafka.producer;

import com.example.kafkawebsocketchatapp.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

}

