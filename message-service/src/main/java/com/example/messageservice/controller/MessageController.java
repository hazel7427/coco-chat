package com.example.messageservice.controller;

import com.example.messageservice.dto.MessageRequest;
import com.example.messageservice.entity.Message;
import com.example.messageservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(messageService.sendMessage(request));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessage(id));
    }
} 