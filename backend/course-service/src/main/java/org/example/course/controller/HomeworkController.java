package org.example.course.controller;


import org.example.course.model.Homework;
import org.example.course.model.HomeworkGrade;
import org.example.course.service.HomeworkService;
import org.example.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private AuthService authService;  // 确保注入
    @PostMapping
    public ResponseEntity<?> submitHomework(
            @PathVariable String courseId,
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token) {
        String studentId = authService.validateToken(token);

        // 校验截止时间
        if (homeworkService.isDeadlinePassed(courseId)) {
            throw new RuntimeException("已超过提交截止时间");
        }

        // 保存文件
        String filePath = homeworkService.saveHomeworkFile(file);
        homeworkService.submitHomework(studentId, courseId, filePath);

        return ResponseEntity.ok("作业提交成功");
    }

    @GetMapping
    public ResponseEntity<List<Homework>> getHomeworkStatus(
            @PathVariable String courseId,
            @RequestHeader("Authorization") String token) {
        String studentId = authService.validateToken(token);
        List<Homework> submissions = homeworkService.getSubmissions(studentId, courseId);
        return ResponseEntity.ok(submissions);
    }
    // 批量下载作业（ZIP打包）
    @GetMapping("/{courseId}/download")
    public ResponseEntity<Resource> downloadAllHomework(@PathVariable String courseId) {
        Resource zipFile = homeworkService.packageHomeworkZip(courseId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=homework.zip")
                .body(zipFile);
    }

    // 提交评分
    @PostMapping("/grade")
    public ResponseEntity<?> gradeHomework(@RequestBody HomeworkGrade grade) {
        homeworkService.gradeHomework(grade);
        return ResponseEntity.ok("评分已保存");
    }
}