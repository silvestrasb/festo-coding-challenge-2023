package com.festo.codingchallenge2023.chapter.tutorial.util;

import java.util.List;

public class ChallengeUtils {

    /**
     * Works provided the string in the parameter contains only letters from the alphabet.
     * <p>
     * Chapter: Tutorial
     * Puzzle: 1 (0.1 The Key Maker)
     */
    public static boolean isStringOrdered(String string) {
        for (int i = 0; i < string.length() - 1; ) {
            if (alphabetPosition(string.charAt(i)) > alphabetPosition(string.charAt(++i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Works, provided you input only chars that are in the alphabet.
     * <p>
     * Chapter: Tutorial
     * Puzzle: 1 (0.1 The Key Maker)
     */
    private static int alphabetPosition(char c) {
        int codeOfLowercaseA = 'a';
        int codeOfLowerCaseInputChar = Character.toLowerCase(c);
        return codeOfLowerCaseInputChar - codeOfLowercaseA + 1;
    }

    /**
     * Works, provided you input only chars that are in the alphabet.
     * <p>
     * Chapter: Tutorial
     * Puzzle: 3 (0.3 Traps)
     */
    public static List<String> splitLog(String log) {
        // This strips the string of the first 6 characters that are irrelevant for this operation.
        String strippedString = log.substring(6);

        return List.of(strippedString.split(" "));
    }
}
