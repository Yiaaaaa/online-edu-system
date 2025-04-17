package org.example.course.service;


import org.example.course.model.Course;
import org.example.course.model.Homework;
import org.example.course.model.HomeworkGrade;
import org.example.course.repository.CourseRepository;
import org.example.course.repository.HomeworkRepository;
import org.example.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AuthService authService;  // 确保已注入

    @Override
    public boolean isDeadlinePassed(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));
        LocalDateTime deadline = course.getHomeworkDeadline();
        return LocalDateTime.now().isAfter(deadline);
    }

    @Override
    public void submitHomework(String studentId, String courseId, String filePath) {
        Homework homework = new Homework();
        homework.setStudentId(studentId);
        homework.setCourseId(courseId);
        homework.setFilePath(filePath);
        homework.setSubmitTime(LocalDateTime.now());
        homework.setStatus("待批改");
        homeworkRepository.save(homework);
    }

    @Override
    public List<Homework> getSubmissions(String studentId, String courseId) {
        return homeworkRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public Resource packageHomeworkZip(String courseId) {
        List<Homework> submissions = homeworkRepository.findByCourseId(courseId);
        Path zipPath = Paths.get("temp/homework_" + courseId + ".zip");
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            for (Homework hw : submissions) {
                Path filePath = Paths.get(hw.getFilePath());
                zos.putNextEntry(new ZipEntry(filePath.getFileName().toString()));
                Files.copy(filePath, zos);
                zos.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException("打包作业失败: " + e.getMessage());
        }
        return new FileSystemResource(zipPath.toFile());
    }

    @Override
    public void gradeHomework(HomeworkGrade grade) {
        Homework homework = homeworkRepository.findById(grade.getHomeworkId())
                .orElseThrow(() -> new RuntimeException("作业不存在"));
        homework.setStatus("已评分");
        homeworkRepository.save(homework);
        // 保存评分详情（需实现 HomeworkGradeRepository）
    }
    private final Path fileStorageLocation = Paths.get("uploads/homework").toAbsolutePath().normalize();

    @Override
    public String saveHomeworkFile(MultipartFile file) {
        try {
            String fileName = String.format("%s_%s", UUID.randomUUID(), file.getOriginalFilename());
            Path targetPath = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("文件保存失败: " + e.getMessage());
        }
    }
}