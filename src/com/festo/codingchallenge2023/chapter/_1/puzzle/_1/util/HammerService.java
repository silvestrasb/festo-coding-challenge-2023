package com.festo.codingchallenge2023.chapter._1.puzzle._1.util;

import com.festo.codingchallenge2023.chapter._1.puzzle._1.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Hammer;

import java.util.List;

public class HammerService {

    public List<Hammer> hammerList;

    public HammerService(List<Hammer> hammerList) {
        this.hammerList = hammerList;
    }

    public Hammer getHammerByIndex(int hammerIndex) {
        return hammerList.stream()
                .filter(hammer -> hammer.getIndex() == hammerIndex)
                .findFirst()
                .orElseThrow(
                        () -> new InvalidInstructionException(String.format("Hammer with the index %s does not exist", hammerIndex))
                );
    }
}
