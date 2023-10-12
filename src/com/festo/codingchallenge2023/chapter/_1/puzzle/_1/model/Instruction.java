package com.festo.codingchallenge2023.chapter._1.puzzle._1.model;

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
