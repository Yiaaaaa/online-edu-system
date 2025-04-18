package org.example.user.model;

import org.example.course.model.Course;
import org.example.course.model.Homework;

import java.util.*;

public class Teacher {
    private String teacherId;
    private String name;
    private List<Course> createdCourses = new ArrayList<>(); // 创建的课程

    public Teacher(String teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
    }

    // 创建课程
    public Course createCourse(String title, String category, double price) {
        Course course = new Course(title, category, price, teacherId);
        createdCourses.add(course);
        return course;
    }

    // 批改作业
    public void gradeHomework(Homework homework, int score, String feedback) {
        homework.setScore(score);
        homework.setFeedback(feedback);
        homework.setGraded(true);
    }

    // Getters
    public String getTeacherId() { return teacherId; }
    public String getName() { return name; }
    public List<Course> getCreatedCourses() { return Collections.unmodifiableList(createdCourses); }
}