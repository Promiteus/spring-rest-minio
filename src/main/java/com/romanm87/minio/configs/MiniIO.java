package com.romanm87.minio.configs;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiniIO {
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint("http://localhost:9000").credentials("", "").build();
    }
}
