package com.festo.codingchallenge2023.chapter._1.puzzle._1.util;

import com.festo.codingchallenge2023.chapter._1.puzzle._1.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Hammer;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Instruction;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Recipe;

import java.util.Objects;

public class KeyMakerShop {

    public static String keyBaseSegment = "A";
    public HammerService hammerService;

    public KeyMakerShop(HammerService hammerService) {
        this.hammerService = hammerService;
    }

    public String makeKey(Recipe recipe) {
        return recipe.getInstructionList()
                .stream()
                .reduce(keyBaseSegment,
                        (key, instruction) -> executeInstruction(instruction, key),
                        (key1, key2) -> key2);
    }

    private String executeInstruction(Instruction instruction, String key) {
        Hammer hammer = hammerService.getHammerByIndex(instruction.getHammerIndex());
        if (instruction.getPosition() > key.length()) {
            throw new InvalidInstructionException("Invalid Instruction!");
        }
        String letter = String.valueOf(key.charAt(instruction.getPosition() - 1));
        if (!Objects.equals(hammer.getInput(), letter)) {
            throw new InvalidInstructionException("Invalid Instruction!");
        }
        return insertToKey(key, hammer.getOutput(), instruction.getPosition());
    }

    private String insertToKey(String key, String forgeOutput, int index) {
        String keyBegin = key.substring(0, index - 1);
        String keyEnd = key.substring(index);
        return keyBegin + forgeOutput + keyEnd;
    }

}
