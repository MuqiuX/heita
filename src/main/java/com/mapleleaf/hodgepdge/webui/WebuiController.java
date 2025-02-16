package com.mapleleaf.hodgepdge.webui;

import com.mapleleaf.hodgepdge.config.AppConfigs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import com.mapleleaf.hodgepdge.common.utils.ScriptsRunner;

import java.io.IOException;

@Controller
public class WebuiController {
    private final AppConfigs appConfigs;

    public WebuiController(AppConfigs appConfigs){
        this.appConfigs = appConfigs;
    }

    @GetMapping("/webui")
    public Rendering toWebui() throws IOException, InterruptedException {
        ScriptsRunner.runScript(ScriptsRunner.WEBUI_SCRIPT, "-x");
        return Rendering.redirectTo(appConfigs.getWebuiUrl()).build();
    }
}
