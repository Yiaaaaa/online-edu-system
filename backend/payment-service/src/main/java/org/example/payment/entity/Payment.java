package org.example.payment.entity;

import java.time.LocalDateTime;

public class Payment {
    private String orderId;
    private double amount;
    private String status; // PENDING, SUCCESS, FAILED, REFUNDED
    private LocalDateTime createTime;
    private LocalDateTime expireTime;

    public Payment(double amount) {
        this.orderId = "PAY-" + System.currentTimeMillis();
        this.amount = amount;
        this.status = "PENDING";
        this.createTime = LocalDateTime.now();
        this.expireTime = createTime.plusMinutes(30); // 30分钟过期
    }

    // 处理支付
    public void processPayment(String paymentMethod) {
        if (LocalDateTime.now().isAfter(expireTime)) {
            throw new IllegalStateException("订单已过期");
        }
        this.status = "SUCCESS";
    }

    // 退款
    public void refund() {
        this.status = "REFUNDED";
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}