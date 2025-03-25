package com.example.messageservice.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private String content;
    private String sender;
    private String recipient;
} 