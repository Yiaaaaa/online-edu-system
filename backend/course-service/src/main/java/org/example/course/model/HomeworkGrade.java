package org.example.course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HomeworkGrade {
    @Id
    private String id;
    private String homeworkId;
    private int score; // 百分制
    private String feedback;
    private String template; // 评分模板JSON，如 {"代码规范":30,"功能完成度":70}
    // Getter 方法
    public String getHomeworkId() {
        return homeworkId;
    }

    // Setter 方法（可选）
    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }
}