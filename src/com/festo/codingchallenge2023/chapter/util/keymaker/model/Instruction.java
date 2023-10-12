package com.festo.codingchallenge2023.chapter.util.keymaker.model;

public class Instruction {

    private final int hammerIndex;
    private final int position;

    public Instruction(int hammerIndex, int position) {
        this.hammerIndex = hammerIndex;
        this.position = position;
    }

    public int getHammerIndex() {
        return hammerIndex;
    }

    public int getPosition() {
        return position;
    }
}
