package org.example.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 向指定用户发送实时通知
    public void sendToUser(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", message);
    }
}