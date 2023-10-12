package com.festo.codingchallenge2023.chapter._1.puzzle._1;

import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Recipe;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.HammerService;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.KeyService;
import com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileReader;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {

    public static String KEYMAKER_RECIPE_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\11_keymaker_recipe.txt";
    public static String KEYMAKER_HAMMER_CONFIG_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\hammer_collection.txt";

    public static void main(String[] args) throws IOException {
        List<Recipe> recipeList = KeyMakerFileReader.initializeRecipeList(KEYMAKER_RECIPE_ABS_PATH);
        List<Hammer> hammerList = KeyMakerFileReader.initializeHammerList(KEYMAKER_HAMMER_CONFIG_ABS_PATH);
        HammerService hammerService = new HammerService(hammerList);
        KeyService keyService = new KeyService(hammerService);

        recipeList.stream()
                .map(recipe -> {
                    try {
                        return keyService.makeKey(recipe);
                    } catch (InvalidInstructionException e) {
                        // Not the most elegant way, but oh well.
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresentOrElse(
                        key -> System.out.printf("The key is: %s%n", key),
                        () -> System.out.println("No valid recipe was found.")
                );
    }
}
