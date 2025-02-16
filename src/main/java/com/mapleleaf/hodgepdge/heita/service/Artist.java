package com.mapleleaf.hodgepdge.heita.service;

import com.mapleleaf.hodgepdge.common.Heita;
import com.mapleleaf.hodgepdge.pojo.DrawHeitaRequest;
import com.mapleleaf.hodgepdge.pojo.StableDiffusionProcessingTxt2Img;
import com.mapleleaf.hodgepdge.pojo.TextToImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class Artist {
    private final WebClient webClient;
    public Artist(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<TextToImageResponse>> draw(DrawHeitaRequest drawHeitaRequest) {
        StableDiffusionProcessingTxt2Img stableDiffusionProcessingTxt2Img = new StableDiffusionProcessingTxt2Img();
        stableDiffusionProcessingTxt2Img.setPrompt(drawHeitaRequest.getPrompt() + "," + Heita.lora);

        return webClient.post()
                .uri("/sdapi/v1/txt2img")
                .bodyValue(stableDiffusionProcessingTxt2Img)
                .retrieve()
                .toEntity(TextToImageResponse.class);
    }
}
