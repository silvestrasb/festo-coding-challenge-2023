package com.festo.codingchallenge2023.chapter._1.puzzle._3.util.file;

import com.festo.codingchallenge2023.chapter._1.puzzle._3.model.Trap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ChallengeInitializer {

    public static List<Trap> initializeTraps(String pathToResource) {
        List<Trap> trapList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToResource));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                trapList.add(TrapParser.parseTrap(line));
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return trapList;

    }
}
