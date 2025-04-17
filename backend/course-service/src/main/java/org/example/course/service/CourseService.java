package org.example.course.service;

import org.example.course.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> findCourses(String category);
    Course getCourseById(String courseId);
}