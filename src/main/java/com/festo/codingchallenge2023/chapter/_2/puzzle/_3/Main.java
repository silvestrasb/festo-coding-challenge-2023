package com.festo.codingchallenge2023.chapter._2.puzzle._3;

import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;
import com.festo.codingchallenge2023.chapter.util.general.model.Fraction;
import com.festo.codingchallenge2023.chapter.util.general.service.EgyptianFractionCalculator;
import com.festo.codingchallenge2023.chapter.util.general.service.MathFractionUtil;
import com.festo.codingchallenge2023.chapter.util.trap.file.TrapFileReader;
import com.festo.codingchallenge2023.chapter.util.trap.model.ObscuredTrap;
import com.festo.codingchallenge2023.chapter.util.trap.model.Trap;
import com.festo.codingchallenge2023.chapter.util.trap.validation.WeightedTrapSafetyChecker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    private static final String TRAP_CONFIG_WEIGHT_PLACEHOLDER_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_3\\resource\\23_trap_right_side.txt";

    public static void main(String[] args) {
        List<ObscuredTrap> obscuredTrapList = TrapFileReader.initializeObscuredTraps(PathResolver.getAbsPath(TRAP_CONFIG_WEIGHT_PLACEHOLDER_REL_PATH));
        EgyptianFractionCalculator egyptianFractionCalculator = new EgyptianFractionCalculator();

        Integer sumOfIds = obscuredTrapList.stream()
                .map(trap -> egyptianFractionCalculator.possibleUnitFractionSums(
                                        trap.noOfWeightsOnTheRight,
                                        trap.leftWeightList.stream()
                                                .map(weight -> new Fraction(1L, weight))
                                                .reduce(new Fraction(0L, 1L),
                                                        MathFractionUtil::addTwoFractions)
                                ).stream()
                                .map(egyptianFractionList -> egyptianFractionList.stream()
                                        .map(Fraction::denominator)
                                        .collect(Collectors.toList())
                                )
                                .map(denominatorList -> new Trap(trap.leftWeightList, denominatorList, trap.id))
                                .filter(WeightedTrapSafetyChecker::isSafe)
                                .findFirst()
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(trap -> trap.id)
                .reduce(0, Integer::sum);


        System.out.printf("Sum of valid trap id's is %d.", sumOfIds.intValue());

    }
}
