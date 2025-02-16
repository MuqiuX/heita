package com.mapleleaf.hodgepdge.pojo;


import com.mapleleaf.hodgepdge.common.Heita;
import lombok.Data;

import java.util.List;

/**
 * StableDiffusionProcessingTxt2Img
 */
@Data
public class StableDiffusionProcessingTxt2Img {
    private int batch_size = 1;
    private double cfg_scale = 7;
    private double denoising_strength = 0;
    private boolean enable_hr = false;
    private double eta = 0;
    private int firstphase_height = 0;
    private int firstphase_width = 0;
    private int height = 512;
    private int n_iter = 1;
    private String negative_prompt = Heita.embeddings;
    private String prompt = Heita.lora;
    private boolean restore_faces = false;
    private double s_churn = 0;
    private double s_noise = 1;
    private double s_tmax = 0;
    private double s_tmin = 0;
    private String sampler_index = "Euler a";
    private long seed = -1;
    private int seed_resize_from_h = -1;
    private int seed_resize_from_w = -1;
    private int steps = 20;
    private List<String> styles = Heita.styles;
    private long subseed = -1;
    private double subseed_strength = 0;
    private boolean tiling = false;
    private int width = 512;
}