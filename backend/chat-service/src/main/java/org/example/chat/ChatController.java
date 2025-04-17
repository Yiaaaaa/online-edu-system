package org.example.chat;

import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/send/{courseId}")
    @SendTo("/topic/chat/{courseId}")
    public Message sendMessage(
            @DestinationVariable String courseId,
            @Payload Message message,
            Principal principal) {
        // 验证用户是否属于该课程
        if (!chatService.isUserInCourse(principal.getName(), courseId)) {
            throw new AccessDeniedException("无权限发送消息");
        }
        message.setTimestamp(LocalDateTime.now());
        chatService.saveMessage(message);
        return message;
    }
}