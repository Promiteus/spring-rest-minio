package com.romanm87.minio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/file")
public class UploadFilesController {

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucket") String bucket) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download/{bucket}/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) {
        return ResponseEntity.ok().build();
    }
}
