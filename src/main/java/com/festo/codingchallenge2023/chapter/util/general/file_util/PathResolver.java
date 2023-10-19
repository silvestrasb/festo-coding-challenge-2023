package com.festo.codingchallenge2023.chapter.util.general.file_util;

import java.io.File;

public class PathResolver {

    public static String getAbsPath(String relativePAth){
        return String.format("%s\\%s", getWorkingDirectory(), relativePAth);
    }
    private static String getWorkingDirectory(){
        return new File("").getAbsolutePath();
    }
}
