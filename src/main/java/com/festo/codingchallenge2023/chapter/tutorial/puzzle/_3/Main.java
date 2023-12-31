package com.festo.codingchallenge2023.chapter.tutorial.puzzle._3;

import com.festo.codingchallenge2023.chapter.tutorial.puzzle._3.util.TrapSafetyChecker;
import com.festo.codingchallenge2023.chapter.tutorial.util.ChallengeUtils;
import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    private final static String CHALLENGE_FILE_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\tutorial\\puzzle\\_3\\resource\\03_trap_logs.txt";

    public static void main(String[] args) throws IOException {
        int lineNumber = 1;
        Integer sumOfLogIDs = 0;
        String consoleOutputPattern = "The log %s found at the line %d indicates the trap is %s.";
        String consoleOutputPatternEnd = "The sum of the ID numbers of traps is: %d";
        String readLine = "";


        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(PathResolver.getAbsPath(CHALLENGE_FILE_REL_PATH)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while (readLine != null) {
            try {
                readLine = br.readLine();
                Integer logLineNumber = Integer.valueOf(readLine.substring(0, 4).strip());

                List<String> trapLog = ChallengeUtils.splitLog(readLine);

                if (TrapSafetyChecker.isTheTrapStateSafe(trapLog)) {
                    System.out.printf((consoleOutputPattern) + "%n", trapLog, lineNumber, "Safe");
                    sumOfLogIDs += logLineNumber;
                } else {
                    System.out.printf((consoleOutputPattern) + "%n", trapLog, lineNumber, "Unsafe");
                }
                lineNumber++;
            } catch (Exception e) {
                br.close();
            }
        }

        System.out.printf((consoleOutputPatternEnd) + "%n", sumOfLogIDs);
    }
}
