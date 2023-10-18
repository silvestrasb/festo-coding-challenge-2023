package com.festo.codingchallenge2023.chapter.util.general.service;

import com.festo.codingchallenge2023.chapter.util.general.file.PathResolver;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EgyptianFractionCalculator {


    public static final String EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH = "festo-coding-challenge-2023\\src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_3\\js\\egyptianFunctionFixedLenghtCalculator.js";

    public static void foo() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");
        try {
            engine.eval(
                    Files.newBufferedReader(Paths.get(PathResolver.getAbsPath(EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH)),
                            StandardCharsets.UTF_8)
            );

            Invocable inv = (Invocable) engine;
            inv.invokeFunction("efFXDlen", 5, 5, 4, 100, 100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
