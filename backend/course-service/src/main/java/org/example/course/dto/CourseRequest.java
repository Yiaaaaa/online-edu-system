package org.example.course.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public class CourseRequest {
    @NotBlank(message = "课程标题不能为空")
    private String title;

    @NotBlank(message = "课程分类不能为空")
    private String category;

    @PositiveOrZero(message = "价格不能为负数")
    private Double price;

    @NotNull(message = "开课时间不能为空")
    private LocalDateTime startTime;

    // Getter 和 Setter 方法
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
}