package org.example.course.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:8081") // 假设支付服务运行在 8081 端口
public interface PaymentServiceClient {
    /**
     * 检查学生是否已支付某课程
     * @param courseId 课程ID
     * @param studentId 学生ID
     * @return 支付状态（true=已支付）
     */
    @GetMapping("/payments/check")
    boolean isPaid(@RequestParam("courseId") String courseId, @RequestParam("studentId") String studentId);
}