package com.mapleleaf.hodgepdge.FileLoader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Slf4j
@Service
public class SaveFiles {
    private final Path todayDirPath;

    public SaveFiles(Path todayDirPath) {
        this.todayDirPath = todayDirPath;
    }

    public Mono<Void> save(FilePart filePart) throws RuntimeException{
        return filePart.transferTo(todayDirPath.resolve(filePart.filename()).normalize())
                .doOnSuccess(unused -> log.info("File saved successfully: {}", filePart.filename()))
                .onErrorResume(ex -> {
                    log.error("Failed to save file: {}", ex.getMessage(), ex);
                    return Mono.empty();
                });
    }

}
