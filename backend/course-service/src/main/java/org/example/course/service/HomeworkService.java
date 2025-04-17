package org.example.course.service;


import org.example.course.model.Homework;
import org.example.course.model.HomeworkGrade;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HomeworkService {
    boolean isDeadlinePassed(String courseId);
    void submitHomework(String studentId, String courseId, String filePath);
    List<Homework> getSubmissions(String studentId, String courseId);
    Resource packageHomeworkZip(String courseId);
    void gradeHomework(HomeworkGrade grade);
    String saveHomeworkFile(MultipartFile file); // 新增方法
}