package org.example.course.service;

import org.example.course.model.Course;
import org.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findCourses(String category) {
        if (category == null || category.isEmpty()) {
            return courseRepository.findAll();
        }
        return courseRepository.findByCategory(category);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
    }
}