package com.festo.codingchallenge2023.chapter._1.puzzle._1.util.file;

import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Hammer;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Instruction;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Recipe;

import java.util.List;

public class TextFileParser {

    public static Recipe parseRecipe(String fileLine) {
        Recipe recipe = new Recipe();
        List<String> listOfUnparsedInstructions = List.of(fileLine.split(" - "));
        List<Instruction> instructionList = listOfUnparsedInstructions.stream()
                .map(stringInstruction -> stringInstruction.substring(1, stringInstruction.length() - 1))
                .map(stringInstruction -> stringInstruction.split(", "))
                .map(List::of)
                .map(unparsedList -> {
                    int hammerIndex = Integer.parseInt(unparsedList.get(0));
                    int keyPosition = Integer.parseInt(unparsedList.get(1));
                    return new Instruction(hammerIndex, keyPosition);
                }).toList();

        return new Recipe(instructionList);
    }


    public static Hammer parseHammerConfig(String fileLine) {
        int index = Integer.parseInt(fileLine.substring(0, 1));
        String hammerInput = fileLine.substring(3, 4);
        String hammerOutput = fileLine.substring(8, 10);
        return new Hammer(index, hammerInput, hammerOutput);
    }
}
