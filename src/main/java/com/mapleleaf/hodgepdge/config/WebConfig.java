package com.mapleleaf.hodgepdge.config;

import com.mapleleaf.hodgepdge.common.utils.PathUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.nio.file.Path;

@Configuration
public class WebConfig {
    private final AppConfigs appConfigs;

    public WebConfig(AppConfigs appConfigs) {
        this.appConfigs = appConfigs;
    }

    @Bean
    public Path storageRootPath(){
        Path path = Path.of(PathUtil.getUserHomeDir() + File.separator + appConfigs.getStorageDir());
        return PathUtil.createFile(path);
    }

    @Bean
    public Path todayDirPath() {
        Path path = storageRootPath().resolve(PathUtil.getTodayDir());
        return PathUtil.createFile(path);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8000")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs()
                                .maxInMemorySize(10 * 1024 * 1024))
                        .build())
                .build();
    }

    @Bean
    public WebClient myWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8001")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs()
                                .maxInMemorySize(10 * 1024 * 1024))
                        .build())
                .build();
    }
}
