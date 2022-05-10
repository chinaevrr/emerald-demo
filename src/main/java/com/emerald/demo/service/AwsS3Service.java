package com.emerald.demo.service;

public interface AwsS3Service {
    void putTextFile(String bucketName, String path, String content);
}
