package com.hackaton.telemedicine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleApiConfig {

    @Value("${google.credentials.file.path}")
    private String credentialsFilePath;

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }
}
