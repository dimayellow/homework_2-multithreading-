package ru.digitalhabbits.homework2.utils.impl;

import ru.digitalhabbits.homework2.utils.LetterCounter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LetterInStringCounter implements LetterCounter {

    private String entryString;

    public LetterInStringCounter(String entryString) {
        this.entryString = entryString;
    }

    @Override
    public Map<Character, Long> call() {
        return count();
    }

    @Override
    public Map<Character, Long> count() {

        if (entryString == null || entryString.isEmpty()) {
            return Collections.emptyMap();
        } else {
            return countCharsInString();
        }
    }

    private Map<Character, Long> countCharsInString() {

        Map<Character, Long> reply = new HashMap<>();
        entryString.chars()
                .forEach(ch -> reply.compute((char) ch, (k, v) -> v == null ? 1L : ++v));
        return reply;

    }


}
