package ru.digitalhabbits.homework2.counters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.HashMap;
import java.util.Map;

class LetterInStringCounterTest {

    @ParameterizedTest
    @NullAndEmptySource
    public void whenSendEmptyStringOrNull_returnEmptyMap(String emptyString) {

        var testObject = new LetterInStringCounter(emptyString);

        var result = testObject.count();

        Assertions.assertTrue(result.isEmpty());

    }

    @Test
    public void wgenSendString_returnCountOfLitters() {

        String testString = "qqqw rrw";

        Map<Character, Long> expectedMap = new HashMap<>();
        expectedMap.put('q', 3L);
        expectedMap.put('w', 2L);
        expectedMap.put('r', 2L);
        expectedMap.put(' ', 1L);

        var testObject = new LetterInStringCounter(testString);

        var result = testObject.count();

        Assertions.assertEquals(expectedMap, result);


    }

}