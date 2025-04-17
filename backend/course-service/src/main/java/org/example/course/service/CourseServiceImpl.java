package org.example.course.service;

import org.example.course.dto.CourseRequest;
import org.example.course.model.Course;
import org.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Override
    public Course createCourse(CourseRequest request, String teacherId) {
        // 1. 将 DTO 转换为实体
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setCategory(request.getCategory());
        course.setPrice(request.getPrice());
        course.setStartTime(request.getStartTime());
        course.setTeacherId(teacherId); // 设置教师ID
        course.setPublished(false);     // 默认保存为草稿

        // 2. 保存到数据库
        return courseRepository.save(course);
    }
    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    @Autowired
    private FileStorageService fileStorageService;

    public String storeFile(String courseId, MultipartFile file, String type) {
        return fileStorageService.storeFile(courseId, file, type);
    }
}