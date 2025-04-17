package org.example.user.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final String SECRET_KEY = "your-secret-key";

    // 验证 Token 并返回用户ID
    public String validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        return claims.getSubject();
    }

    // 获取当前教师ID（假设 Token 中包含角色信息）
    public String getCurrentTeacherId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        return claims.get("teacherId", String.class);
    }
}