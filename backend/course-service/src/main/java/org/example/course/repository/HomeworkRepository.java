package org.example.course.repository;

import org.example.course.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, String> {
    // 根据学生ID和课程ID查询作业
    List<Homework> findByStudentIdAndCourseId(String studentId, String courseId);

    // 根据课程ID查询所有作业
    List<Homework> findByCourseId(String courseId);
}