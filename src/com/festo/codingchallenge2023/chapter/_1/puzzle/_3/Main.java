package com.festo.codingchallenge2023.chapter._1.puzzle._3;

import com.festo.codingchallenge2023.chapter._1.puzzle._3.model.Trap;
import com.festo.codingchallenge2023.chapter._1.puzzle._3.util.file.ChallengeInitializer;
import com.festo.codingchallenge2023.chapter._1.puzzle._3.util.validation.WeightedTrapSafetyChecker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String TRAP_WEIGHT_CONFIG_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_3\\resource\\13_trap_balance.txt";

    public static void main(String[] args) {
        List<Trap> trapList = ChallengeInitializer.initializeTraps(TRAP_WEIGHT_CONFIG_ABS_PATH);
        List<Trap> safeTraps = new ArrayList<>();

        for (Trap trap : trapList) {
            System.out.printf("Trap with index %d ", trap.getId());
            if (WeightedTrapSafetyChecker.isSafe(trap)) {
                safeTraps.add(trap);
                System.out.println("Safe.");
                continue;
            }
            System.out.println("Unsafe.");
        }

        System.out.printf("The number of traps scanned %d.\n", trapList.size());
        System.out.printf("The number of safe traps found %d.\n", safeTraps.size());
        Integer sumOfSafeTrapIds = safeTraps.stream()
                .map(Trap::getId)
                .reduce(0, Integer::sum);
        System.out.printf("The sum of ID's of safe trap's is %d\n", sumOfSafeTrapIds);
    }
}
