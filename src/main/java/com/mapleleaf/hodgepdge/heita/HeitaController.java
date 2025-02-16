package com.mapleleaf.hodgepdge.heita;

import com.mapleleaf.hodgepdge.heita.service.Artist;
import com.mapleleaf.hodgepdge.heita.service.Identifier;
import com.mapleleaf.hodgepdge.pojo.DrawHeitaRequest;
import com.mapleleaf.hodgepdge.pojo.TextToImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(value = "/heita")
public class HeitaController {
    private final Artist artist;
    private final Identifier identifier;
    public HeitaController(Artist artist, Identifier identifier) {
        this.artist = artist;
        this.identifier = identifier;
    }

    @PostMapping(value = "/identify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Boolean> identifyHeita(@RequestPart(value = "files")FilePart file) {
        log.info("Identify heita file");
        return identifier.identify(file);
    }

    @PostMapping(value = "/draw", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Flux<String> drawHeita(@RequestBody(required = false)DrawHeitaRequest drawHeitaRequest) {
        if (drawHeitaRequest == null) {
            return Flux.error(new IllegalArgumentException("Request body is required"));
        }

        log.info("Received draw request: {}", drawHeitaRequest);

        return artist.draw(drawHeitaRequest)
                .flatMapIterable(entity -> {
                    TextToImageResponse response = entity.getBody();
                    if (response != null && response.getImages() != null) {
                        return response.getImages();
                    }
                    return Collections.emptyList();
                })
                .onErrorResume(e -> {
                    log.error("Error occurred while drawing: ", e);
                    return Flux.error(e);
                });
    }
}
