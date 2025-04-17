package org.example.course.controller;

import org.example.course.model.Course;
import org.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    // 获取所有课程（支持分类筛选）
    @GetMapping
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(required = false) String category) {
        List<Course> courses = courseService.findCourses(category);
        return ResponseEntity.ok(courses);
    }

    // 获取课程详情
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable String courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
}