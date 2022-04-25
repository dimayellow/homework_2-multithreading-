package ru.digitalhabbits.homework2.utils.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.digitalhabbits.homework2.utils.LineHandler;

import java.io.File;
import java.util.*;

import static com.google.common.io.Resources.getResource;

class FileHandlerImplTest {

    @Test
    public void whenSendFile_correctlyHandleAllLines() throws Exception {

        LineHandler testHandler = Mockito.mock(LineHandler.class);

        var aStringMock = getHandleMock(firstLineMap());
        Mockito.when(testHandler.getNewHandler("aaab")).thenReturn(aStringMock);

        var bStringMock = getHandleMock(secondLineMap());
        Mockito.when(testHandler.getNewHandler("gggss")).thenReturn(bStringMock);

        var cStringMock = getHandleMock(thirdLineMap());
        Mockito.when(testHandler.getNewHandler("asw")).thenReturn(cStringMock);

        var file = new File(getResource("testFrileReaderImpl").getPath());
        var testObject = new FileHandlerImpl(testHandler);

        List<Map<Character, Long>> expectedList = new ArrayList<>();
        expectedList.add(firstLineMap());
        expectedList.add(secondLineMap());
        expectedList.add(thirdLineMap());

        Assertions.assertEquals(testObject.readLines(file), expectedList);

    }

    private LineHandler getHandleMock(Map<Character, Long> characterLongMap) throws Exception {
        LineHandler mock = Mockito.mock(LineHandler.class);
        Mockito.when(mock.call()).thenReturn(characterLongMap);
        return mock;
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