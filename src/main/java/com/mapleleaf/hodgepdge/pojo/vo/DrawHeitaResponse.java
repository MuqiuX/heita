package com.mapleleaf.hodgepdge.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class DrawHeitaResponse {
    /**
     * Image，The generated image in base64 format.
     */
    private List<String> images;
}
