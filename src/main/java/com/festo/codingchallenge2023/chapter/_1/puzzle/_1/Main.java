package com.festo.codingchallenge2023.chapter._1.puzzle._1;

import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;
import com.festo.codingchallenge2023.chapter.util.keymaker.exception.InvalidInstructionException;
import com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileReader;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Recipe;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.HammerService;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.KeyService;

import java.util.List;
import java.util.Objects;

public class Main {

    public static String KEYMAKER_RECIPE_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\11_keymaker_recipe.txt";
    public static String KEYMAKER_HAMMER_CONFIG_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_1\\puzzle\\_1\\resource\\hammer_collection.txt";

    public static void main(String[] args) {
        List<Recipe> recipeList = KeyMakerFileReader.initializeRecipeList(PathResolver.getAbsPath(KEYMAKER_RECIPE_REL_PATH));
        List<Hammer> hammerList = KeyMakerFileReader.initializeHammerList(PathResolver.getAbsPath(KEYMAKER_HAMMER_CONFIG_REL_PATH));

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
