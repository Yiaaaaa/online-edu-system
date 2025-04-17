package org.example.course.service;

import org.example.course.dto.CourseRequest;
import org.example.course.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    List<Course> findCourses(String category);
    Course getCourseById(String courseId);

    Course createCourse(CourseRequest request, String teacherId);
    Course saveCourse(Course course);
    String storeFile(String courseId, MultipartFile file, String type);
}