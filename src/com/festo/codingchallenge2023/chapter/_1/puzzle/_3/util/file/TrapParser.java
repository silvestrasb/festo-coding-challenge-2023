package com.festo.codingchallenge2023.chapter._1.puzzle._3.util.file;

import com.festo.codingchallenge2023.chapter._1.puzzle._3.model.Trap;

import java.util.Arrays;
import java.util.List;

public class TrapParser {
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

}
