package ru.digitalhabbits.homework2.utils;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Counter characters in given string
 */
public interface LineHandler extends Callable<Map<Character, Long>> {

    Map<Character, Long> handle();
    void setLine(String line);
    LineHandler getNewHandler(String line);

}
