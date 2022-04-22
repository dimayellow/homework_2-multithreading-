package ru.digitalhabbits.homework2.mergers;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class LetterCountMergerImpl implements LetterCountMerger {

    private final Map<Character, Long> first;
    private final Map<Character, Long> second;

    @Override
    public Map<Character, Long> merge() {

        Map<Character, Long> reply;

        if (itsEmptyMap(first) || itsEmptyMap(second))
            reply = new HashMap<>(mergeWithEmptyOrNyllMap());
        else {
            reply = mergeNotEmptyMap();
        }

        return reply;
    }

    private Map<Character, Long> mergeNotEmptyMap() {

        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey
                        , Map.Entry::getValue
                        , Long::sum));
    }

    private Map<Character, Long> mergeWithEmptyOrNyllMap() {
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
