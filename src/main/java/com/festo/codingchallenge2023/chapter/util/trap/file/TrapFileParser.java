package com.festo.codingchallenge2023.chapter.util.trap.file;

import com.festo.codingchallenge2023.chapter.util.trap.model.ObscuredTrap;
import com.festo.codingchallenge2023.chapter.util.trap.model.Trap;

import java.util.Arrays;
import java.util.List;

public class TrapFileParser {
    public static Trap parseTrap(String fileLine) {
        Integer index = Integer.valueOf(fileLine.substring(0, 4).strip());
        List<List<Long>> trapWeightListBothSides = Arrays.stream(fileLine.substring(6)
                        .split(" - "))
                .map(unparsedWeightList -> Arrays.stream(unparsedWeightList.split(" "))
                        .map(Long::parseLong)
                        .toList()
                ).toList();

        return new Trap(trapWeightListBothSides.get(0), trapWeightListBothSides.get(1), index);
    }

    public static ObscuredTrap parseObscuredTrap(String fileLine) {
        Integer index = Integer.valueOf(fileLine.substring(0, 4).strip());
        List<List<String>> trapWeightListBothSides = Arrays.stream(fileLine.substring(6)
                        .split(" - "))
                .map(unparsedWeightList -> Arrays.stream(unparsedWeightList.split(" "))
                        .toList()
                ).toList();

        List<Long> leftSideWeights = trapWeightListBothSides.get(0)
                .stream()
                .map(Long::parseLong)
                .toList();
        Integer noOfWeightsOnTheRight = trapWeightListBothSides.get(1).size();
        return new ObscuredTrap(leftSideWeights, noOfWeightsOnTheRight, index);
    }

}
