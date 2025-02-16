package com.mapleleaf.hodgepdge.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PathUtil {
    public static Path resolvePath(String dir, String fileName) {
        Path path = Path.of(dir, fileName);
        if (!Files.exists(path)){
            try{    Files.createDirectories(path);
            } catch (IOException e) {    throw new RuntimeException(e);
            }
        }
        return path;
    }

    public static String getUserHomeDir() {
        return System.getProperty("user.home");
    }

    public static String getTodayDir() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Path createFile(Path path) {
        if(!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return path;
    }
}
