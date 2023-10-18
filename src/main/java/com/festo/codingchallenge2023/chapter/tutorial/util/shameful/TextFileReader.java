package com.festo.codingchallenge2023.chapter.tutorial.util.shameful;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Terribly inefficient class for reading.
 * <p>
 * Trash class. I feel shame for having coded this (crying noises).
 */
public class TextFileReader {

    private BufferedReader br = null;

    public TextFileReader(String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine(Integer lineNumber) throws IOException {
        for (int i = 0; i < lineNumber - 1; i++) {
            br.readLine();
        }
        String specifiedLine = br.readLine();
        return specifiedLine;
    }

    public void close() throws IOException {
        br.close();
    }
}
