package com.romanm87.minio.dto;

import lombok.Data;

@Data
public class Violation {
    private final String fieldName;
    private final String message;
}
