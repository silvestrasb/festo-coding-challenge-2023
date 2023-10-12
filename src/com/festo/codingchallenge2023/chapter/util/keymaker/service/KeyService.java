package com.festo.codingchallenge2023.chapter.util.keymaker.service;

import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidKeyException;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Instruction;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Recipe;

import java.util.*;
import java.util.stream.Collectors;

public class KeyService {

    public static String keyBaseSegment = "A";
    public HammerService hammerService;

    /*
    Hammer service is unnecessary, move it to here.
     */
    public KeyService(HammerService hammerService) {
        this.hammerService = hammerService;
    }

    /**
     * Finds all possible @key initial states.
     *
     * @param key
     * @return
     */
    public Set<String> reduceKey(String key) {
        Set<String> possibleReductions = new HashSet<>();
        for (int i = 0; i < key.length() - 1; i++) {
            String pair = key.charAt(i) + String.valueOf(key.charAt(i + 1));
            int finalI = i;
            possibleReductions.addAll(
                    hammerService.getHammerList()
                            .stream()
                            .filter(hammer -> hammer.output().equals(pair))
                            .map(hammer -> key.substring(0, finalI) + (hammer.input()) + key.substring(finalI + 2))
                            .toList()
            );
        }

        if (possibleReductions.isEmpty()){
            return Set.of(key);
        }

        return possibleReductions.stream()
                .map(this::reduceKey)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
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
        if (!Objects.equals(hammer.input(), letter)) {
            throw new InvalidInstructionException("Invalid Instruction!");
        }
        return insertToKey(key, hammer.output(), instruction.getPosition());
    }

    private String insertToKey(String key, String forgeOutput, int index) {
        String keyBegin = key.substring(0, index - 1);
        String keyEnd = key.substring(index);
        return keyBegin + forgeOutput + keyEnd;
    }

}

