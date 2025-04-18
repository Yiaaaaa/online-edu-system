package org.example.user.model;

import org.example.course.model.Course;
import org.example.course.model.Homework;

import java.time.LocalDateTime;
import java.util.*;

public class Student {
    private String studentId;
    private String name;
    private Set<Course> enrolledCourses = new HashSet<>(); // 已选课程
    private Map<Course, List<Homework>> homeworkSubmissions = new HashMap<>(); // 课程作业提交记录

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // 选课方法（需检查是否已支付）
    public void enrollCourse(Course course, boolean isPaid) {
        if (course.isPaid() && !isPaid) {
            throw new IllegalArgumentException("付费课程需要先完成支付");
        }
        enrolledCourses.add(course);
        course.addStudent(this);
    }

    // 提交作业
    public void submitHomework(Course course, Homework homework) {
        if (!enrolledCourses.contains(course)) {
            throw new IllegalStateException("未选此课程，无法提交作业");
        }
        homework.setSubmitTime(LocalDateTime.now());
        homeworkSubmissions.computeIfAbsent(course, k -> new ArrayList<>()).add(homework);
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public Set<Course> getEnrolledCourses() { return Collections.unmodifiableSet(enrolledCourses); }
}