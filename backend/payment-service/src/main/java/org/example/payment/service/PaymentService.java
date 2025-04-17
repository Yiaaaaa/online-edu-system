package org.example.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public boolean isPaid(String courseId, String studentId) {
        // 根据订单表查询支付状态
        return paymentRepository.existsByCourseIdAndStudentIdAndStatus(courseId, studentId, "PAID");
    }
}