package com.festo.codingchallenge2023.chapter.util.keymaker.model;

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
