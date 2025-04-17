package org.example.chat.service;

import org.example.chat.repository.MessageRepository;
import org.example.chat.entity.Message;
import org.example.course.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // 验证用户是否属于某课程
    public boolean isUserInCourse(String studentId, String courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }
    @Autowired
    private MessageRepository messageRepository;  // 注入MessageRepository
    // 保存聊天记录到数据库
    public void saveMessage(Message message) {
        // 假设已注入 MessageRepository
        messageRepository.save(message);
    }
}