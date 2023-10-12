package com.festo.codingchallenge2023.chapter._1.puzzle._1;

import com.festo.codingchallenge2023.chapter._1.puzzle._1.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Hammer;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.model.Recipe;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.util.HammerService;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.util.KeyMakerShop;
import com.festo.codingchallenge2023.chapter._1.puzzle._1.util.file.TextFileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static String KEYMAKER_RECIPE_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\11_keymaker_recipe.txt";
    public static String KEYMAKER_HAMMER_CONFIG_ABS_PATH = "C:\\Appl\\repository\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\hammer_collection.txt";

    public static void main(String[] args) throws IOException {
        List<Recipe> recipeList = initializeRecipeList(KEYMAKER_RECIPE_ABS_PATH);
        List<Hammer> hammerList = initializeHammerList(KEYMAKER_HAMMER_CONFIG_ABS_PATH);
        HammerService hammerService = new HammerService(hammerList);
        KeyMakerShop keyMakerShop = new KeyMakerShop(hammerService);

        recipeList.stream()
                .map(recipe -> {
                    try {
                        return keyMakerShop.makeKey(recipe);
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

    private static List<Hammer> initializeHammerList(String pathToHammerConfig) throws IOException {
        List<Hammer> hammerList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToHammerConfig));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                hammerList.add(TextFileParser.parseHammerConfig(line));
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return hammerList;

    }

    private static List<Recipe> initializeRecipeList(String pathToFile) throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        String readLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                recipes.add(TextFileParser.parseRecipe(line));
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return recipes;
    }
}
