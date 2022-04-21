package ru.digitalhabbits.homework2.impl;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor
class LetterCountMergerImplTest {

    final LetterCountMerger testObject = new LetterCountMergerImpl();

    @ParameterizedTest
    @ArgumentsSource(EmptyOrNullMatrix.class)
    public void whenSendEmptyOrNullMatrix_returnEmptyMatrix(Map<Character, Long> first, Map<Character, Long> second) {

        var result = testObject.merge(first, second);

        Assertions.assertEquals(result, Collections.emptyMap());

    }

    static class EmptyOrNullMatrix implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(null, null),
                    Arguments.of(null, new HashMap<Character, Long>()),
                    Arguments.of(new HashMap<Character, Long>(), new HashMap<Character, Long>())
            );
        }
    }

    // --------

    @Test
    public void whenSendEmptyAndNotEmptyMap_returnNotEmpty() {

        Map<Character, Long> testMap = new HashMap<>();
        testMap.put('n', 2l);

        var result1 = testObject.merge(testMap, null);
        var result2 = testObject.merge(Collections.emptyMap(), testMap);

        Assertions.assertAll(
                () -> assertEquals(result1, testMap),
                () -> assertEquals(result2, testMap)
        );

    }

    // --------

    @ParameterizedTest
    @ArgumentsSource(NotNullMatrix.class)
    public void whenSendTwoNotEmptyMap_merge(Map<Character, Long> first, Map<Character, Long> second, Map<Character, Long> expectedResult) {

        var result = testObject.merge(first, second);

        Assertions.assertEquals(result, expectedResult);
    }

    static class NotNullMatrix implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(firstMap(), secondMap(), firstPlusSecondMap()),
                    Arguments.of(firstMap(), thirdMap(), firstPlusThirdMap())
            );
        }

        private Map<Character, Long> firstMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 2l);
            map.put('c', 4l);
            map.put('g', 1l);
            return map;
        }

        private Map<Character, Long> secondMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 1l);
            map.put('b', 1l);
            map.put('c', 5l);
            return map;
        }

        private Map<Character, Long> firstPlusSecondMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 3l);
            map.put('b', 1l);
            map.put('c', 9l);
            map.put('g', 1l);
            return map;
        }

        private Map<Character, Long> thirdMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('k', 2l);
            return map;
        }

        private Map<Character, Long> firstPlusThirdMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 2l);
            map.put('c', 4l);
            map.put('g', 1l);
            map.put('k', 2l);
            return map;
        }
    }

    // --------
}