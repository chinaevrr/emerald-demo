package com.emerald.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "person.aws.s3")
@Data
public class PersonS3Properties {
    private String bucketName;
}
