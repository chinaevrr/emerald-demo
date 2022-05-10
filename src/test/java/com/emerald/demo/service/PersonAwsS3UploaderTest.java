package com.emerald.demo.service;

import com.emerald.demo.component.PathGenerator;
import com.emerald.demo.config.PersonS3Properties;
import com.emerald.demo.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonAwsS3UploaderTest {
    public static final String FILE_PATH = "filePath";
    public static final String BUCKET_NAME = "bucketName";
    public static final String CONTENT = "content";
    @InjectMocks
    private PersonAwsS3Uploader personAwsS3Uploader;
    @Mock
    private Message<?> message;
    @Mock
    private Person person;
    @Mock
    private PathGenerator pathGenerator;
    @Mock
    private PersonS3Properties s3Properties;
    @Mock
    private AwsS3Service awsS3Service;
    @Mock
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        when(pathGenerator.generatePath(any())).thenReturn(FILE_PATH);
        when(s3Properties.getBucketName()).thenReturn(BUCKET_NAME);
        when(mapper.writeValueAsString(any())).thenReturn(CONTENT);
    }

    @Test
    void shouldInvokeUpload() {
        personAwsS3Uploader.uploadFile(message, person);

        verify(awsS3Service).putTextFile(BUCKET_NAME, FILE_PATH, CONTENT);
    }
}
