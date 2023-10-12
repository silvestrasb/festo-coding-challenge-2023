package com.festo.codingchallenge2023.chapter.tutorial.puzzle._1;

import com.festo.codingchallenge2023.chapter.tutorial.util.ChallengeUtils;
import com.festo.codingchallenge2023.chapter.tutorial.util.shameful.TextFileReader;

import java.io.IOException;

public class Main {

    public final static String CHALLENGE_FILE_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\tutorial\\puzzle\\_1\\resource\\01_keymaker_ordered.txt";

    public static void main(String[] args) throws IOException {
        TextFileReader textFileReader = new TextFileReader(CHALLENGE_FILE_ABS_PATH);
        int limit = 10000;
        int lineNumber = 1;
        String consoleOutputPattern = "String %s found at the line %d is %s.";
        while (lineNumber < limit) {
            String readLine = textFileReader.readLine(lineNumber);
            if (ChallengeUtils.isStringOrdered(readLine)) {
                System.out.printf((consoleOutputPattern) + "%n", readLine, lineNumber, "Ordered");
                System.out.println("Stopping the program.");
                break;
            }
            System.out.printf((consoleOutputPattern) + "%n", readLine, lineNumber, "UnOrdered");
            if (lineNumber == limit) {
                System.out.printf("The limit of %t was reached, the program was stopped.%n", limit);
            }
            lineNumber++;

        }
        textFileReader.close();
    }
}
