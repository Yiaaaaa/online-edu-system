package org.example.course.repository;

import org.example.course.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    // 检查学生是否已选某课程
    boolean existsByStudentIdAndCourseId(String studentId, String courseId);
}