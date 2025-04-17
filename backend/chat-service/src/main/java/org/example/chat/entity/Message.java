package org.example.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String courseId;
    private String senderId;
    private String content;
    private LocalDateTime timestamp;  // 新增字段
    private boolean isResolved;  // 新增字段：是否已解决

    // Getter & Setter
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    // Getter 和 Setter 方法
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public boolean isResolved() { return isResolved; }
    public void setResolved(boolean resolved) { isResolved = resolved; }
}