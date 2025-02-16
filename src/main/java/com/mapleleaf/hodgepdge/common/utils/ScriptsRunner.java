package com.mapleleaf.hodgepdge.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ScriptsRunner {
    private static final String shellInterpreter = "bash";
    public static final String WEBUI_SCRIPT = "retransmission";
    public static final String PORT_CHECK_SCRIPT = "port_check";

    public static boolean runScript(String scriptName, String... args) throws IOException, InterruptedException {

        //获取脚本路径
        File script = new File(PathUtil.getUserHomeDir() + File.separator + "scripts" + File.separator + scriptName.concat(".sh"));

        //命令构建
        List<String> commend = new ArrayList<>();
        commend.add(ScriptsRunner.shellInterpreter);
        commend.add(script.getAbsolutePath());
        commend.addAll(List.of(args));

        // 创建进程并执行脚本
        Process process = new ProcessBuilder(commend).start();

        // 读取脚本输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            log.info("Script---webui---:{}", line);
        }

        // 等待脚本执行完成
        return process.waitFor() == 0;
    }
}
