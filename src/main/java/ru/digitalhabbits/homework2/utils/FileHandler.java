package ru.digitalhabbits.homework2.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;


public interface FileHandler {

    List<Future<Map<Character, Long>>> readLines(File file) throws IOException;

}
