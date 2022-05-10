package com.emerald.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AwsS3ServiceImplTest {
    public static final String BUCKET_NAME = "bucketName";
    public static final String PATH = "path";
    public static final String CONTENT = "content";
    @Mock
    private AmazonS3 amazonS3;
    @InjectMocks
    private AwsS3ServiceImpl s3Service;

    @Test
    void shouldInvokeAmazonS3PutObject() {
        s3Service.putTextFile(BUCKET_NAME, PATH, CONTENT);
        Mockito.verify(amazonS3).putObject(BUCKET_NAME, PATH, CONTENT);
    }
}
