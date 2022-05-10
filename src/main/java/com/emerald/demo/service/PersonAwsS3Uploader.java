package com.emerald.demo.service;

import com.emerald.demo.component.PathGenerator;
import com.emerald.demo.config.PersonS3Properties;
import com.emerald.demo.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonAwsS3Uploader {
    private final PathGenerator pathGenerator;
    private final AwsS3Service awsS3Service;
    private final PersonS3Properties s3Properties;
    private final ObjectMapper mapper;

    @SneakyThrows
    public void uploadFile(Message<?> message, Person createdPerson) {
        String path = pathGenerator.generatePath(message);

        log.info("Save person to s3 bucket {} with path {}", s3Properties.getBucketName(), path);

        awsS3Service.putTextFile(s3Properties.getBucketName(), path, mapper.writeValueAsString(createdPerson));
    }
}
