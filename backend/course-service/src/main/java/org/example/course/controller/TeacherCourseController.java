package org.example.course.controller;

import io.jsonwebtoken.Jwts;
import org.example.course.model.Course;
import org.example.course.service.CourseService;
import org.example.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/teacher/courses")
public class TeacherCourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private AuthService authService;  // 确保已注入
    // 创建课程（草稿）
    @PostMapping("/draft")
    public ResponseEntity<Course> createDraft(@RequestBody Course course) {
        // 从 token 中解析教师ID（示例逻辑）
        String token = Jwts.builder().setSubject("user").compact();
        String teacherId = authService.getCurrentTeacherId(token);
        course.setTeacherId(teacherId);    // 设置教师ID
        course.setPublished(false);        // 草稿状态未发布
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    // 上传视频/课件
    @PostMapping("/{courseId}/upload")
    public ResponseEntity<?> uploadMaterials(
            @PathVariable String courseId,
            @RequestParam("file") MultipartFile file,
            @RequestParam String type) { // type: video/material
        if (file.getSize() > 2 * 1024 * 1024 * 1024L) {
            throw new RuntimeException("文件大小不能超过2GB");
        }
        String path = courseService.storeFile(courseId, file, type);
        return ResponseEntity.ok(Map.of("path", path));
    }

    // 发布课程
    @PostMapping("/{courseId}/publish")
    public ResponseEntity<?> publishCourse(@PathVariable String courseId) {
        Course course = courseService.getCourseById(courseId);
        course.setPublished(true);    // 标记为已发布
        courseService.saveCourse(course);
        return ResponseEntity.ok("课程已发布");
    }
}