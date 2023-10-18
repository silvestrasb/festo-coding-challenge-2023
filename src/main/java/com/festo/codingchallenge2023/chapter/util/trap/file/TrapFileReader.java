package com.festo.codingchallenge2023.chapter.util.trap.file;

import com.festo.codingchallenge2023.chapter.util.trap.file.TrapFileParser;
import com.festo.codingchallenge2023.chapter.util.trap.model.ObscuredTrap;
import com.festo.codingchallenge2023.chapter.util.trap.model.Trap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TrapFileReader {

    public static List<Trap> initializeTraps(String pathToResource) {
        List<Trap> trapList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToResource));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                trapList.add(com.festo.codingchallenge2023.chapter.util.trap.file.TrapFileParser.parseTrap(line));
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return trapList;
    }

    public static List<ObscuredTrap> initializeObscuredTraps(String pathToResource) {
        List<ObscuredTrap> trapList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToResource));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                trapList.add(TrapFileParser.parseObscuredTrap(line));
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return trapList;
    }

}
