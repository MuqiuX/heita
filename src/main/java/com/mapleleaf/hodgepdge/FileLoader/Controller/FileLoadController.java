package com.mapleleaf.hodgepdge.FileLoader.Controller;

import com.mapleleaf.hodgepdge.FileLoader.service.SaveFiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class FileLoadController {


    private final SaveFiles saveFiles;

    public FileLoadController(SaveFiles saveFiles) {
        this.saveFiles = saveFiles;
    }

    @ResponseBody
    @PostMapping(value = "/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)

    public Flux<String> fileLoad(@RequestPart(value = "files") Flux<FilePart> files) {
        log.info("{}", Thread.currentThread().getName());
        return files.flatMap(filePart -> {
            String fileName = filePart.name();
            return saveFiles.save(filePart)
                    .map(result -> String.format("File '%s' processed: %s", fileName, result))
                    .onErrorResume(error -> Mono.just(String.format("File '%s' failed: %s", fileName, error.getMessage())));
        });
    }
}
