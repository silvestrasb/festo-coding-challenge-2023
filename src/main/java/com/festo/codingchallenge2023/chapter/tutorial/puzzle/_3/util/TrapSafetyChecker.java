package com.festo.codingchallenge2023.chapter.tutorial.puzzle._3.util;

import java.util.List;

public class TrapSafetyChecker {

    private static final List<String> safeWords = List.of("inactive", "disabled", "quiet", "standby", "idle");

    // Excess provided unused info
    private static final List<String> dangerousWords = List.of("live", "armed", "ready", "primed", "active");

    private static final List<String> flipWords = List.of("flipped", "toggled", "reversed", "inverted", "switched");


    public static boolean isTheTrapStateSafe(List<String> trapLog) {
        // Assuming that trap is activated from the beginning.
        // No details regarding this information were provided in the challenge description.
        boolean isTrapActive = true;

        for (String singleLog : trapLog) {
            isTrapActive = isFlipping(singleLog) ? !isTrapActive : isSafe(singleLog);
        }
        return isTrapActive;
    }

    private static boolean isSafe(String word) {
        return safeWords.contains(word);
    }

    private static boolean isFlipping(String word) {
        return flipWords.contains(word);
    }

}
