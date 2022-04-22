package ru.digitalhabbits.homework2.fileHandlers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static com.google.common.io.Resources.getResource;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerImplTest {

    @Test
    public void whenSendFile_correctlyHandleAllLines() throws IOException, ExecutionException, InterruptedException {

        var file = new File(getResource("testFrileReaderImpl").getPath());
        var testObject = new FileHandlerImpl();

        Set<Map<Character, Long>> expectedSet = new HashSet<>();
        expectedSet.add(firstLineMap());
        expectedSet.add(secondLineMap());
        expectedSet.add(thirdLineMap());

        var result = testObject.readLines(file);

        Set<Map<Character, Long>> resultSet = new HashSet<>();

        for (Future<Map<Character, Long>> f : result) {
            resultSet.add(f.get());
        }

        Assertions.assertEquals(resultSet, expectedSet);
        assertEquals(result.size(), 3);

    }

    private Map<Character, Long> firstLineMap() {
        Map<Character, Long> map = new HashMap<>();
        map.put('a', 3L);
        map.put('b', 1L);

        return map;
    }

    private Map<Character, Long> secondLineMap() {
        Map<Character, Long> map = new HashMap<>();
        map.put('g', 3L);
        map.put('s', 2L);

        return map;
    }

    private Map<Character, Long> thirdLineMap() {
        Map<Character, Long> map = new HashMap<>();
        map.put('a', 1L);
        map.put('s', 1L);
        map.put('w', 1L);

        return map;
    }

}