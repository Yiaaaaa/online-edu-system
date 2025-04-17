package org.example.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FAQ {
    @Id
    private String id;
    private String courseId;
    private String question;
    private String answer;
    // Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}