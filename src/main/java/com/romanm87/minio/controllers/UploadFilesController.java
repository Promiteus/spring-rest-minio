package com.romanm87.minio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/file")
public class UploadFilesController {

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download")
    public ResponseEntity<?> getFile() {
        return ResponseEntity.ok().build();
    }
}
