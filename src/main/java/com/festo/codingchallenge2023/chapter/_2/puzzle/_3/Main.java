package com.festo.codingchallenge2023.chapter._2.puzzle._3;

import com.festo.codingchallenge2023.chapter.util.general.file.PathResolver;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static final String TRAP_CONFIG_WEIGHT_PLACEHOLDER_REL_PATH = "C:\\Appl\\repository\\local\\festo-coding-challenge-2023\\src\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_3\\resource\\23_trap_right_side.txt";
    public static final String EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH = "C:\\Appl\\repository\\local\\festo-coding-challenge-2023\\src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_3\\js\\egyptianFunctionFixedLenghtCalculator.js";

    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");
        engine.eval(
                Files.newBufferedReader(Paths.get(PathResolver.getAbsPath(EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH)),
                StandardCharsets.UTF_8)
        );
        Invocable inv = (Invocable) engine;
        inv.invokeFunction("efFXDlen", 5, 5, 4, 100, 100);

    }
}
