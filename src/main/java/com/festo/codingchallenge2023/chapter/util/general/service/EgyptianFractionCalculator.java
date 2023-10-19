package com.festo.codingchallenge2023.chapter.util.general.service;

import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;
import com.festo.codingchallenge2023.chapter.util.general.model.Fraction;
import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EgyptianFractionCalculator {

    private static final String EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH = "src/main/java/com/festo/codingchallenge2023/chapter/util/general/service/resource/js/egyptianFunctionFixedLenghtCalculator.js";
    private static final String JS_ENGINE_NAME = "graal.js";

    private final ScriptEngineManager manager;
    private final ScriptEngine engine;

    public EgyptianFractionCalculator() {
        this.manager = new ScriptEngineManager();
        HostAccess access = HostAccess.newBuilder(HostAccess.ALL).targetTypeMapping(Value.class, Object.class, Value::hasArrayElements, v -> new LinkedList<>(v.as(List.class))).build();
        this.engine = GraalJSScriptEngine.create(null,
                Context.newBuilder("js")
                        .allowHostAccess(access)
        );
    }

    /**
     * Returns all possible unit fraction sums of provided {@code fraction} of provided {@code length}.
     *
     * @param length
     * @param fraction
     * @return
     */
    public List<List<Fraction>> possibleUnitFractionSums(Integer length, Fraction fraction) {
        List<String> efFXDlen;
        try {
            engine.eval(
                    Files.newBufferedReader(Paths.get(PathResolver.getAbsPath(EGYPTIAN_FUNCTION_CALCULATOR_REL_PATH)),
                            StandardCharsets.UTF_8)
            );
            Invocable inv = (Invocable) engine;
            efFXDlen = (List<String>) inv.invokeFunction("efFXDlen", length, fraction.numerator(), fraction.denominator());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return efFXDlen.stream()
                .map(EgyptianFractionJsParser::parseLine)
                .collect(Collectors.toList());
    }

    static class EgyptianFractionJsParser {
        protected static List<Fraction> parseLine(String line) {
            String rightEquationSide = line.split(" = ")[1];
            return new ArrayList<>(
                    Arrays.stream(rightEquationSide.split(" \\+ "))
                            .map(fractionString -> {
                                List<String> fractionPartList = List.of(fractionString.split("/"));
                                Long numerator = Long.valueOf(fractionPartList.get(0));
                                Long denominator = Long.valueOf(fractionPartList.get(1));
                                return new Fraction(numerator, denominator);
                            }).collect(Collectors.toList()));
        }


    }

}












































