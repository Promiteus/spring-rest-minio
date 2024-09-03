package com.romanm87.minio.controllers;

import com.romanm87.minio.dto.FileResource;
import com.romanm87.minio.services.FileService;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/api/file")
public class UploadFilesController {
    private final FileService fileService;

    public UploadFilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<FileResource> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("bucket") String bucket) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResponseEntity.ok(this.fileService.singleUpload(file, bucket));
    }

    @GetMapping(value = "/download/{bucket}/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResponseEntity.ok(this.fileService.singleDownload(bucket, fileName));
    }
}
