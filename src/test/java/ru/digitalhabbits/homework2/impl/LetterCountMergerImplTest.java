package ru.digitalhabbits.homework2.impl;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.digitalhabbits.homework2.mergers.LetterCountMergerImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@NoArgsConstructor
class LetterCountMergerImplTest {


    @ParameterizedTest
    @ArgumentsSource(EmptyOrNullMatrix.class)
    public void whenSendEmptyOrNullMatrix_returnEmptyMatrix(Map<Character, Long> first, Map<Character, Long> second) {

        var testObject = new LetterCountMergerImpl(first, second);

        var result = testObject.merge();

        Assertions.assertEquals(result, Collections.emptyMap());

    }

    static class EmptyOrNullMatrix implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(null, null),
                    Arguments.of(null, new HashMap<Character, Long>()),
                    Arguments.of(new HashMap<Character, Long>(), new HashMap<Character, Long>())
            );
        }
    }

    // --------

    @ParameterizedTest
    @ArgumentsSource(NotNullMatrix.class)
    public void whenSendAtLeastOneNonEmptyMap_mergeEqualsResult(Map<Character, Long> first, Map<Character, Long> second, Map<Character, Long> expectedResult) {

        var testObject = new LetterCountMergerImpl(first, second);

        var result = testObject.merge();

        Assertions.assertEquals(result, expectedResult);
    }

    static class NotNullMatrix implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(firstMap(), secondMap(), firstPlusSecondMap()),
                    Arguments.of(firstMap(), thirdMap(), firstPlusThirdMap()),
                    Arguments.of(firstMap(), null, firstMap()),
                    Arguments.of(Collections.emptyMap(), firstMap(), firstMap())
            );
        }

        private Map<Character, Long> firstMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 2L);
            map.put('c', 4L);
            map.put('g', 1L);
            return map;
        }

        private Map<Character, Long> secondMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 1L);
            map.put('b', 1L);
            map.put('c', 5L);
            return map;
        }

        private Map<Character, Long> firstPlusSecondMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 3L);
            map.put('b', 1L);
            map.put('c', 9L);
            map.put('g', 1L);
            return map;
        }

        private Map<Character, Long> thirdMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('k', 2L);
            return map;
        }

        private Map<Character, Long> firstPlusThirdMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 2L);
            map.put('c', 4L);
            map.put('g', 1L);
            map.put('k', 2L);
            return map;
        }
    }

    // --------

    @Test
    public void whenWeSendMap_returnOtherObject() {

        Map<Character, Long> map = new HashMap<>();
        map.put('k', 2L);

        var testObject = new LetterCountMergerImpl(map, null);

        var result = testObject.merge();

        Assertions.assertNotSame(map, result);
    }
}
