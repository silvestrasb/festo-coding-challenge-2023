package com.festo.codingchallenge2023.chapter.util.keymaker.service;

import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;

import java.util.List;
import java.util.Map;

public class HammerService {

    public List<Hammer> hammerList;

    public HammerService(List<Hammer> hammerList) {
        this.hammerList = hammerList;
    }

    public Hammer getHammerByIndex(int hammerIndex) {
        return hammerList.stream()
                .filter(hammer -> hammer.index() == hammerIndex)
                .findFirst()
                .orElseThrow(
                        () -> new InvalidInstructionException(String.format("Hammer with the index %s does not exist", hammerIndex))
                );
    }

    public List<Hammer> getHammerList(){
        return this.hammerList;
    }
}
