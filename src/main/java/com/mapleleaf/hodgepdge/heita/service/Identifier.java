package com.mapleleaf.hodgepdge.heita.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Identifier {
    private final WebClient myWebClient;
    public Identifier(WebClient myWebClient) {
        this.myWebClient = myWebClient;
    }

    public Mono<Boolean> identify(FilePart filePart) {
        return myWebClient.post()
                .uri("/identify")
                .bodyValue(filePart)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
