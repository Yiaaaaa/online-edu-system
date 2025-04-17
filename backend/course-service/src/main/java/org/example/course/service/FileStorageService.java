package org.example.course.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("uploads/courses");

    public String storeFile(String courseId, MultipartFile file, String type) {
        try {
            String filename = String.format("%s_%s_%s",
                    courseId, type, UUID.randomUUID() + "_" + file.getOriginalFilename());
            Path targetPath = rootLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("文件存储失败: " + e.getMessage());
        }
    }
}