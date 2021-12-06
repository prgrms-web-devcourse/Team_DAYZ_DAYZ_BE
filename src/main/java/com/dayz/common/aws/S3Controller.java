package com.dayz.common.aws;

import com.dayz.common.dto.ApiResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    @Value("${cloud.aws.s3.bucket_path}")
    private String BUCKET_ADDRESS;

    private final AwsS3Service awsS3Service;

    @PostMapping("/api/v1/images")
    public ApiResponse createBoards(@RequestParam(value = "files", required = false) MultipartFile multipartFile) throws IOException {
        String uploadedUrl = awsS3Service.upload(multipartFile);

        return ApiResponse.ok(Map.of("imageUrl",uploadedUrl));
    }

}
