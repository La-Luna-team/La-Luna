package com.laluna.laluna.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.laluna.laluna.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Logger 관련 임포트 추가
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final AmazonS3Client amazonS3Client;
    private final PhotoService photoService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private Long getMidValue() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return Long.parseLong(authentication.getName());
        } else {
            throw new IllegalStateException("로그인한 사용자의 mid를 찾을 수 없습니다.");
        }
    }

    // Logger 생성
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String link = "board11/" + fileName;

            Long mid = getMidValue();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, link, file.getInputStream(), metadata);

            // Logger를 사용하여 로그 출력
            logger.info("File uploaded successfully. Link: {}", link);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, link, file.getInputStream(), metadata);
            amazonS3Client.putObject(putObjectRequest);

            String imageUrl = amazonS3Client.getUrl(bucket, link).toString();
            photoService.savePhotoUrlToDB(link, mid);

            return ResponseEntity.ok(link);
        } catch (IOException e) {
            // 로그를 사용하여 에러 메시지 출력
            logger.error("Error uploading file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
