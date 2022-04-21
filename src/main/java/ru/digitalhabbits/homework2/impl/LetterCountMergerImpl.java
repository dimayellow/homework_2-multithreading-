package ru.digitalhabbits.homework2.impl;

import lombok.NoArgsConstructor;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class LetterCountMergerImpl implements LetterCountMerger {

    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        if (itsEmptyMap(first) && itsEmptyMap(second))
            return new HashMap<Character, Long>();
        return null;
    }

    private boolean itsEmptyMap(Map<Character, Long> map) {
        return (map == null || map.isEmpty());
    }


}
