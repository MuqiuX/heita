package com.mapleleaf.hodgepdge.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfigs {
    private String storageDir;
    private String webuiUrl;
}
