package org.example.chat.entity;

import org.example.course.model.Course;
import org.example.user.model.Student;

import java.time.LocalDateTime;
import java.util.*;

public class ChatRoom {
    private String roomId;
    private Course course; // 关联的课程
    private List<Message> messageHistory = new ArrayList<>();

    public ChatRoom(Course course) {
        this.roomId = "CHAT-" + course.getId();
        this.course = course;
    }

    // 发送消息（需验证用户权限）
    public void sendMessage(Student student, String content) {
        if (!course.getStudents().contains(student)) {
            throw new SecurityException("未选此课程的学生无法发言");
        }
        messageHistory.add(new Message(student.getName(), content, LocalDateTime.now()));
    }

    // 获取历史消息
    public List<Message> getMessageHistory() {
        return Collections.unmodifiableList(messageHistory);
    }

    // 消息嵌套类
    public static class Message {
        private String sender;
        private String content;
        private LocalDateTime timestamp;

        public Message(String sender, String content, LocalDateTime timestamp) {
            this.sender = sender;
            this.content = content;
            this.timestamp = timestamp;
        }

        // Getters
        public String getFormattedMessage() {
            return String.format("[%s] %s: %s",
                    timestamp.toLocalTime(), sender, content);
        }
    }
}