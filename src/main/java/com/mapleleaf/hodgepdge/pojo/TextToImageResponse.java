package com.mapleleaf.hodgepdge.pojo;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class TextToImageResponse {
    private List<String> images;
    private String info;
    private Map<String, Object> parameters;
}
