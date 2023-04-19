package com.teammiri.projecth.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.teammiri.projecth.domain.project.dto.ProjectResponseDto;
import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.repository.ProjectRepository;
import com.teammiri.projecth.domain.project.service.ProjectService;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import com.teammiri.projecth.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final S3Service s3Service;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final AmazonS3 s3client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    public User uploadProfileImage(MultipartFile file, Long userId) throws IOException {
//        User user = userRepository.findById(userId).orElse(null);
//        if (user == null) {
//            // 해당 유저가 존재하지 않을 때 처리할 로직
//            return null;
//        }
//
//        String fileName = file.getOriginalFilename();
//        String key = userId + "/" + fileName;
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(file.getContentType());
//        metadata.setContentLength(file.getSize());
//
//        s3client.putObject(
//                new PutObjectRequest(s3client.getBucketLocation(bucket), key, file.getInputStream(), metadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//
//        String imageUrl = s3client.getUrl("BUCKET_NAME", key).toString();
//        user.setProfileImageUrl(imageUrl);
//
//        return userRepository.save(user);
//    }

//    public void uploadPortfolio(MultipartFile file) {
//        User user = userService.getLoginUser();
//        String fileName = file.getOriginalFilename();
//        String key = user.getUserId() + "/" + fileName;
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(file.getContentType());
//        metadata.setContentLength(file.getSize());
//
//        s3client.putObject(
//                new PutObjectRequest(bucket, key, file.getInputStream(), metadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//
//        String imageUrl = s3client.getUrl("BUCKET_NAME", key).toString();
//        user.setProfileImageUrl(imageUrl);
//
//    }

    public String uploadPortfolio(MultipartFile file) {
        User user = userService.getLoginUser();
        String portfolioUrl = uploadFiles(file, "portfolio");
        user.setPortfolioUrl(portfolioUrl);
        userRepository.save(user);
        return portfolioUrl;
    }

    public String uploadFiles(MultipartFile multipartFile, String dirName) {
        try {
            File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                    .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
            return upload(uploadFile, dirName);
        } catch (IOException e) {
            throw new IllegalArgumentException("error: MultipartFile -> File convert fail");
        }
    }

    public String upload(File uploadFile, String filePath) {
        String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        s3client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return s3client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            System.out.println("File delete success");
            return;
        }
        System.out.println("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        log.info("convert file: " + file.getOriginalFilename());
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        log.info("convertFile: " + convertFile);
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            log.info("converFile.createNewFile() = true");
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                log.info("fos: " + fos);
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    public String uploadProjectProposal(MultipartFile file, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        String proposalUrl = uploadFiles(file, "proposal");
        project.setProposalUrl(proposalUrl);
        projectRepository.save(project);
        return proposalUrl;
    }

    public String uploadProjectImage(MultipartFile file, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        String imageUrl = uploadFiles(file, "project-image");
        project.setProjectImageUrl(imageUrl);
        projectRepository.save(project);
        return imageUrl;
    }
}
