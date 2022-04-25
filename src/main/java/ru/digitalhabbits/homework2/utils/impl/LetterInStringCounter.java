package ru.digitalhabbits.homework2.utils.impl;

import lombok.Setter;
import ru.digitalhabbits.homework2.utils.LineHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LetterInStringCounter implements LineHandler {

    @Setter
    private String line;

    public LetterInStringCounter() {
    }

    @Override
    public LineHandler getNewHandler(String line) {

        LineHandler lineHandler = new LetterInStringCounter();
        lineHandler.setLine(line);

        return lineHandler;
    }

    @Override
    public Map<Character, Long> call() {
        return handle();
    }

    @Override
    public Map<Character, Long> handle() {

        return (line == null || line.isEmpty())
                ? Collections.emptyMap()
                : countCharsInString();

    }




    private Map<Character, Long> countCharsInString() {

        Map<Character, Long> reply = new HashMap<>();
        line.chars()
                .forEach(ch -> reply.compute((char) ch, (k, v) -> v == null ? 1L : ++v));
        return reply;

    }



}
