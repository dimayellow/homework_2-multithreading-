package ru.digitalhabbits.homework2.counters;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Counter characters in given string
 */
public interface LetterCounter extends Callable<Map<Character, Long>> {

    Map<Character, Long> count();

}
