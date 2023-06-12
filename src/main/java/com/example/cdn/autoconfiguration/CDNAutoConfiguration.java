package com.example.cdn.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(CDNProperties.class)
public class CDNAutoConfiguration {

    @Autowired
    CDNProperties properties;

}