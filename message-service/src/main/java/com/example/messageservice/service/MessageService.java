package com.example.messageservice.service;

import com.example.messageservice.dto.MessageRequest;
import com.example.messageservice.entity.Message;
import com.example.messageservice.event.KafkaProducer;
import com.example.messageservice.event.MessageSentEvent;
import com.example.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public Message sendMessage(MessageRequest request) {
        Message message = Message.builder()
                .content(request.getContent())
                .sender(request.getSender())
                .recipient(request.getRecipient())
                .sentAt(LocalDateTime.now())
                .delivered(false)
                .build();

        message = messageRepository.save(message);

        MessageSentEvent event = new MessageSentEvent(
                message.getId(),
                message.getContent(),
                message.getSender(),
                message.getRecipient(),
                message.getSentAt().format(DateTimeFormatter.ISO_DATE_TIME)
        );

        kafkaProducer.sendMessageEvent(event);
        return message;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }
} 