package ru.digitalhabbits.homework2;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AllLetterCounterTest {

    static int treads = Runtime.getRuntime().availableProcessors();

    @ParameterizedTest
    @ArgumentsSource(parameterSet.class)
    public void whenSendListOfMap_returnExpectlyResult(List<Future<Map<Character, Long>>> arrayForCount,
                                                       Map<Character, Long> expectedResult
    ){

        ForkJoinPool pool = new ForkJoinPool(treads);
        var task = new AllLetterCounter(arrayForCount);

        Assertions.assertEquals(expectedResult, pool.invoke(task));

    }

    static class parameterSet implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(
                            new ArrayList<Future<Map<Character, Long>>>(),
                            Collections.emptyMap()
                    ),
                    Arguments.of(
                            listOfMapToListOfFuture(List.of(Collections.emptyMap()))
                            , Collections.emptyMap()
                    ),
                    Arguments.of(
                            listOfMapToListOfFuture(List.of(firstMap()))
                            , firstMap()
                    ),
                    Arguments.of(
                            listOfMapToListOfFuture(List.of(firstMap(), secondMap()))
                            , firstPlusSecondMap()),
                    Arguments.of(
                            listOfMapToListOfFuture(List.of(firstMap(), secondMap(), thirdMap()))
                            , sumFirstToThirdMap()
                    )
            );
        }

        @SneakyThrows
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

        private Map<Character, Long> sumFirstToThirdMap() {
            Map<Character, Long> map = new HashMap<>();
            map.put('a', 3L);
            map.put('b', 1L);
            map.put('c', 9L);
            map.put('g', 1L);
            map.put('k', 2L);
            return map;
        }

        @SneakyThrows
        private List<Future<Map<Character, Long>>> listOfMapToListOfFuture(List<Map<Character, Long>> list) {
            return list.stream().map(this::mapToFuture).collect(Collectors.toList());
        }

        private Future<Map<Character, Long>> mapToFuture(Map<Character, Long> map) {

            Future<Map<Character, Long>> future = Mockito.mock(Future.class);
            try {
                Mockito.when(future.get()).thenReturn(map);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            return future;
        }

    }
}