package com.teammiri.projecth.domain.file.controller;

import com.teammiri.projecth.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload/portfolio")
    public ResponseEntity<?> uploadPortfolio(@RequestParam("file") MultipartFile file) {
        try {
            String portfolioUrl = fileService.uploadPortfolio(file);
            return new ResponseEntity(portfolioUrl, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.warn("파일 업로드 실패", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
