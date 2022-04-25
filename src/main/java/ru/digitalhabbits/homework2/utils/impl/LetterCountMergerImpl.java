package ru.digitalhabbits.homework2.utils.impl;

import lombok.AllArgsConstructor;
import ru.digitalhabbits.homework2.utils.LetterCountMerger;

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

        return (itsEmptyMap(first) || itsEmptyMap(second))
                ? mergeWithEmptyOrNullMap()
                : mergeNotEmptyMap();
    }

    private Map<Character, Long> mergeNotEmptyMap() {

        return Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
    }

    private Map<Character, Long> mergeWithEmptyOrNullMap() {
        if (itsEmptyMap(first) && itsEmptyMap(second)) {
            return Collections.emptyMap();
        } else if (itsEmptyMap(first)) {
            return new HashMap<>(second);
        } else {
            return new HashMap<>(first);
        }
    }

    private boolean itsEmptyMap(Map<Character, Long> map) {
        return (map == null || map.isEmpty());
    }

}
