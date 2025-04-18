package org.example.course.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Homework {
    @Id
    private String id;
    private String studentId;
    private String courseId;
    private String filePath;      // 文件存储路径
    private LocalDateTime submitTime;
    private String status;       // 状态：待批改、已评分
    private Integer score;       // 包装类型允许null
    private String feedback;
    private boolean isGraded;   // 必须与setGraded方法匹配
    // Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public LocalDateTime getSubmitTime() { return submitTime; }
    public void setSubmitTime(LocalDateTime submitTime) { this.submitTime = submitTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    // 添加Setter方法（必须为public）
    public void setScore(int score) {
        this.score = score;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }
}