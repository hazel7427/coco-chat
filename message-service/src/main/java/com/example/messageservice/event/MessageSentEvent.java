package com.example.messageservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSentEvent {
    private Long messageId;
    private String content;
    private String sender;
    private String recipient;
    private String timestamp;
} 