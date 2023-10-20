package com.festo.codingchallenge2023.chapter.util.keymaker.service;

import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Instruction;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Recipe;

import java.util.*;
import java.util.stream.Collectors;

public class KeyService {

    public static String keyBaseSegment = "A";
    public HammerService hammerService;

    private Set<String> usedKeyCache;

    private final List<Hammer> hammerList;

    /*
    Hammer service is unnecessary, move it to here.
     */
    public KeyService(HammerService hammerService) {
        this.hammerService = hammerService;
        this.usedKeyCache = new HashSet<>();
        this.hammerList = hammerService.getHammerList();
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
            Optional<String> optReducedKey = this.hammerList
                    .stream()
                    .filter(hammer -> hammer.output().equals(pair))
                    .findFirst()
                    .map(hammer -> key.substring(0, finalI) + (hammer.input()) + key.substring(finalI + 2))
                    .filter(reducedKey -> !usedKeyCache.contains(reducedKey));
            optReducedKey.ifPresent(reducedKey -> {
                possibleReductions.add(reducedKey);
                usedKeyCache.add(reducedKey);
            });

        }


        if (possibleReductions.isEmpty()) {
            usedKeyCache.add(key);
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

    public void clearCache(){
        this.usedKeyCache.clear();
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

