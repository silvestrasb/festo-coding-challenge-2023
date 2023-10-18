package com.festo.codingchallenge2023.chapter._1.puzzle._3;

import com.festo.codingchallenge2023.chapter.util.general.file.PathResolver;
import com.festo.codingchallenge2023.chapter.util.trap.file.TrapFileReader;
import com.festo.codingchallenge2023.chapter.util.trap.model.Trap;
import com.festo.codingchallenge2023.chapter.util.trap.validation.WeightedTrapSafetyChecker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String TRAP_WEIGHT_CONFIG_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_3\\resource\\13_trap_balance.txt";

    public static void main(String[] args) {
        List<Trap> trapList = TrapFileReader.initializeTraps(PathResolver.getAbsPath(TRAP_WEIGHT_CONFIG_REL_PATH));
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
