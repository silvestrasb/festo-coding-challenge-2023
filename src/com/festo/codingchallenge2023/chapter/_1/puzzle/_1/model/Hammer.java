package com.festo.codingchallenge2023.chapter._1.puzzle._1.model;

public class Hammer {

    private final int index;
    private final String input;
    private final String output;

    public Hammer(int index, String input, String output) {
        this.index = index;
        this.input = input;
        this.output = output;
    }

    public int getIndex() {
        return index;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
