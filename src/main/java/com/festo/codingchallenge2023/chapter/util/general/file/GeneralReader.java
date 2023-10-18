package com.festo.codingchallenge2023.chapter.util.general.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GeneralReader {

    public static List<String> parseToStringList(String pathToFile){
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                stringList.add(line);
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return stringList;
    }

}
