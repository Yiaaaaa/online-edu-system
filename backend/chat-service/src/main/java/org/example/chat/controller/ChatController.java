package org.example.chat.controller;


import org.example.chat.entity.Message;
import org.example.chat.service.ChatService;
import org.example.chat.service.FAQService;
import org.example.chat.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FAQService faqService;
    @MessageMapping("/send/{courseId}")
    @SendTo("/topic/chat/{courseId}")
    public Message sendMessage(
            @DestinationVariable String courseId,
            @Payload Message message,
            Principal principal) throws AccessDeniedException {
        // 验证用户是否属于该课程
        if (!chatService.isUserInCourse(principal.getName(), courseId)) {
            throw new AccessDeniedException("无权限发送消息");
        }
        message.setTimestamp(LocalDateTime.now());
        chatService.saveMessage((Message) message);
        return message;
    }
    @MessageMapping("/teacher/answer")
    public void handleTeacherAnswer(
            @Payload Message message,
            @Header("simpSessionAttributes") Map<String, Object> sessionAttributes
    ) {
        String teacherId = (String) sessionAttributes.get("userId");
        if (message.getContent().startsWith("@")) {
            String studentId = extractMentionedUserId(message.getContent());
            notificationService.sendToUser(studentId, "您被老师@: " + message.getContent());
        }
        chatService.saveMessage(message);

        // 标记为已解决
        if (message.isResolved()) {
            faqService.saveFAQ(
                    message.getCourseId(),
                    message.getContent(),
                    "解答详情请查看聊天记录"
            );
        }
    }
    private String extractMentionedUserId(String content) {
        // 示例：从 "@student123 你好" 中提取 "student123"
        if (content.startsWith("@")) {
            int endIndex = content.indexOf(" ");
            if (endIndex != -1) {
                return content.substring(1, endIndex);
            }
            return content.substring(1);
        }
        return null;
    }
}