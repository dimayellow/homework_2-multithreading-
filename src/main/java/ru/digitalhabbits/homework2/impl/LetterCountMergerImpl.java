package ru.digitalhabbits.homework2.impl;

import lombok.NoArgsConstructor;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class LetterCountMergerImpl implements LetterCountMerger, Runnable {

    @Override
    public void run() {

    }

    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {

        Map<Character, Long> reply = new HashMap<>();

        if (itsEmptyMap(first) || itsEmptyMap(second))
            reply.putAll(mergeWithEmptyOrNyllMap(first, second));
        else {
            reply = mergeNotEmptyMap(first, second);
        }

        return reply;
    }

    private Map<Character, Long> mergeNotEmptyMap(Map<Character, Long> first, Map<Character, Long> second) {

        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey
                        , Map.Entry::getValue
                        , (l1, l2) -> l2 + l1));
    }

    private Map<Character, Long> mergeWithEmptyOrNyllMap(Map<Character, Long> first, Map<Character, Long> second) {
        if (itsEmptyMap(first) && itsEmptyMap(second))
            return Collections.emptyMap();
        else if (itsEmptyMap(first))
            return second;
        else
            return first;
    }

    private boolean itsEmptyMap(Map<Character, Long> map) {
        return (map == null || map.isEmpty());
    }



}
