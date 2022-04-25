package ru.digitalhabbits.homework2.utils.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import ru.digitalhabbits.homework2.utils.LineHandler;

import java.util.HashMap;
import java.util.Map;

class LetterInStringCounterTest {

    LineHandler lineHandler = new LetterInStringCounter();

    @ParameterizedTest
    @NullAndEmptySource
    public void whenSendEmptyStringOrNull_returnEmptyMap(String emptyString) {

        var testObject = lineHandler.getNewHandler(emptyString);

        var result = testObject.handle();

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

        var testObject = lineHandler.getNewHandler(testString);
        var result = testObject.handle();

        Assertions.assertEquals(expectedMap, result);


    }

}