package com.example.messageservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "message-events";

    public void sendMessageEvent(MessageSentEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
} 