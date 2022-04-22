package ru.digitalhabbits.homework2.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

import ru.digitalhabbits.homework2.AllLetterCounter;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.counters.LetterCounter;
import ru.digitalhabbits.homework2.counters.LetterInStringCounter;
import ru.digitalhabbits.homework2.fileHandlers.FileHandler;
import ru.digitalhabbits.homework2.fileHandlers.FileHandlerImpl;
import ru.digitalhabbits.homework2.mergers.LetterCountMerger;
import ru.digitalhabbits.homework2.mergers.LetterCountMergerImpl;

public class AsyncFileLetterCounter implements FileLetterCounter {

    @Override
    public Map<Character, Long> count(File input) {

        Map<Character, Long> reply = new HashMap<>();

        try {
            FileHandler handler = new FileHandlerImpl();
            var parsedFileArray =  handler.readLines(input);

            ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            reply = pool.invoke(new AllLetterCounter(parsedFileArray));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return reply;
    }
}
