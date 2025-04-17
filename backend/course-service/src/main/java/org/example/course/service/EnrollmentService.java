package org.example.course.service;

import org.example.course.client.PaymentServiceClient;
import org.example.course.model.Course;
import org.example.course.model.Enrollment;
import org.example.course.repository.CourseRepository;
import org.example.course.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private PaymentServiceClient paymentServiceClient;

    public void enrollStudent(String courseId, String studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("课程不存在"));

        // 检查是否付费课程且已支付
        if (course.isPaid() && !paymentServiceClient.isPaid(courseId, studentId)) {
            throw new RuntimeException("请先完成支付");
        }

        Enrollment enrollment = new Enrollment(studentId, courseId);
        enrollmentRepository.save(enrollment);
    }
}