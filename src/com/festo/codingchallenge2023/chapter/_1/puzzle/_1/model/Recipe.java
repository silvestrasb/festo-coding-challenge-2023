package com.festo.codingchallenge2023.chapter._1.puzzle._1.model;

import java.util.List;

public class Recipe {

    private List<Instruction> instructionList;

    public Recipe() {
    }

    public Recipe(List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }
}
