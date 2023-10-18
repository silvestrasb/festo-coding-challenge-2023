package com.festo.codingchallenge2023.chapter.util.keymaker.file;

import com.festo.codingchallenge2023.chapter.util.general.file.GeneralReader;
import com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileParser;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Recipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KeyMakerFileReader {

    public static List<String> initializeForgedKeyList(String pathToFile) {
        return GeneralReader.parseToStringList(pathToFile);
    }

    public static List<Hammer> initializeHammerList(String pathToHammerConfig) {
        List<Hammer> hammerList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToHammerConfig));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                hammerList.add(com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileParser.parseHammerConfig(line));
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return hammerList;

    }

    public static List<Recipe> initializeRecipeList(String pathToFile) {
        List<Recipe> recipes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                recipes.add(KeyMakerFileParser.parseRecipe(line));
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return recipes;
    }
}
