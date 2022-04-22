package ru.digitalhabbits.homework2.fileHandlers;

import ru.digitalhabbits.homework2.counters.LetterCounter;
import ru.digitalhabbits.homework2.counters.LetterInStringCounter;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileHandlerImpl implements FileHandler {

    @Override
    public List<Future<Map<Character, Long>>> readLines(File file) throws IOException {


        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<Map<Character, Long>>> futures = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                futures.add(executorService.submit(new LetterInStringCounter(line)));
                line = reader.readLine();
            }
        }

        executorService.shutdown();

        return futures;
    }
}
