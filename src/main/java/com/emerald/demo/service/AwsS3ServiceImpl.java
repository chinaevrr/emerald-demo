package com.emerald.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {
    private final AmazonS3 amazonS3;

    @Override
    public void putTextFile(String bucketName, String path, String content) {
        amazonS3.putObject(bucketName, path, content);
    }
}
