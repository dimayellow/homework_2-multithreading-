package ru.digitalhabbits.homework2.impl;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
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

class LetterCountMergerImplTest {
    @ParameterizedTest
    @ArgumentsSource(EmptyOrNullMatrix.class)
    public void whenSendEmptyOrNullMatrix_returnEmptyMatrix(Map<Character, Long> first, Map<Character, Long> second) {

        LetterCountMerger testObject = new LetterCountMergerImpl();

        var result = testObject.merge(first, second);

        Assertions.assertEquals(result, Collections.emptyMap());
        Assertions.assertTrue(false);
    }

    @NoArgsConstructor
    class EmptyOrNullMatrix implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(null, null),
                    Arguments.of(null, new HashMap<Character, Long>()),
                    Arguments.of(new HashMap<Character, Long>(), new HashMap<Character, Long>())
            );
        }
    }

}