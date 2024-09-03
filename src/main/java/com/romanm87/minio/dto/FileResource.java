package com.romanm87.minio.dto;

import lombok.Data;

@Data
public class FileResource {
    private String fileName;
    private Long size;
    private String filePath;
}
