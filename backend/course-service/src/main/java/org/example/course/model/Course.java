package org.example.course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Course {
    @Id
    private String id;
    private String title;
    private String description;
    private double price;
    private String category;
    private boolean isPaid;
    private boolean isPublished; // 是否已发布
    private String videoPath;    // 视频存储路径
    private String materialsPath; // 课件存储路径
    private LocalDateTime startTime;  // 新增字段：开课时间
    private String teacherId;         // 新增字段：教师ID
    private LocalDateTime homeworkDeadline; // 作业截止时间

    // 默认构造方法（JPA 需要）
    public Course() {}

    // 全参构造方法
    public Course(String id, String title, String description, double price, String category, boolean isPaid) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isPaid = isPaid;

    }

    // Getter 和 Setter 方法
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean published) { isPublished = published; }
    // Getter 方法
    public LocalDateTime getHomeworkDeadline() {
        return homeworkDeadline;
    }

    // Setter 方法（可选）
    public void setHomeworkDeadline(LocalDateTime homeworkDeadline) {
        this.homeworkDeadline = homeworkDeadline;
    }
}