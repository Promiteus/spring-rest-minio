package com.romanm87.minio.configs;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiniIO {
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("WRlv4AYIKYCGqMfUsCfZ", "wpm4xZuYVXEx8zRuXVv8s8zCvYx1s2enl7uAwjzE")
                .build();
    }
}
