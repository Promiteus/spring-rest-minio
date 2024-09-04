package com.romanm87.minio.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
