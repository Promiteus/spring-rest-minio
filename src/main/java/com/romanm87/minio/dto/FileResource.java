package com.romanm87.minio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResource {
    private String fileName;
    private Long size;
    private String filePath;
}
