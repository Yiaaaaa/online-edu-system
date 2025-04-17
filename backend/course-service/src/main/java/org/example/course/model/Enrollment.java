package org.example.course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Enrollment {
    @Id
    private String id;
    private String studentId;  // 字段名与Repository方法参数匹配
    private String courseId;   // 字段名与Repository方法参数匹配

    // 构造方法、Getter和Setter
    public Enrollment(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.id = studentId + "_" + courseId; // 示例ID生成逻辑
    }

    public Enrollment() {

    }

    // Getter 和 Setter 省略...
}