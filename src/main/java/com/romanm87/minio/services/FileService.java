package com.romanm87.minio.services;

import com.romanm87.minio.dto.FileResource;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class FileService {
    private MinioClient minioClient;

    public FileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Сохранить файл в корзине MinIO хранилища
     * @param file MultipartFile
     * @param bucket String
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws InsufficientDataException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws InternalException
     * @throws XmlParserException
     * @throws ErrorResponseException
     */
    public FileResource singleUpload(MultipartFile file, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        FileResource fileResource = FileResource.builder().fileName(file.getOriginalFilename()).filePath(bucket+"/"+file.getOriginalFilename()).size(file.getSize()).build();

        if (this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            this.minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());

            return fileResource;
        }

        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Корзина не найдена!");
    }

    /**
     * Извлечь одиночный файл из MinIO хранилища
     * @param bucket String
     * @param fileName String
     * @return InputStreamResource
     * @throws IOException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws InsufficientDataException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws InternalException
     * @throws XmlParserException
     * @throws ErrorResponseException
     */
    public InputStreamResource singleDownload(String bucket, String fileName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        if (this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            GetObjectResponse objectResponse = this.minioClient.getObject(GetObjectArgs
                    .builder()
                    .bucket(bucket)
                    .object(fileName)
                    .build());


            return new InputStreamResource(objectResponse);
        }

        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Корзина не найдена!");
    }
}
