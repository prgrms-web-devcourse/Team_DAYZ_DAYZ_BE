package com.dayz.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlUtil {

    @Value("${cloud.aws.s3.bucket_path}")
    private String bucketPath;

    public String extractFileName(String imageUrl) {
        if(imageUrl.isBlank()){
            return null;
        }
        return imageUrl.replace(bucketPath, "");
    }

    public String makeImageUrl(String fileName) {
        return bucketPath.concat(fileName);
    }

}

