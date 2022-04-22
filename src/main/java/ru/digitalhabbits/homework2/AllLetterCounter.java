package ru.digitalhabbits.homework2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.mergers.LetterCountMerger;
import ru.digitalhabbits.homework2.mergers.LetterCountMergerImpl;

import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


public class AllLetterCounter extends RecursiveTask<Map<Character, Long>> {

    private List<Future<Map<Character, Long>>> arrayForCount;
    private int startPosition;
    private int endPosition;

    public AllLetterCounter(List<Future<Map<Character, Long>>> arrayForCount) {
        this(arrayForCount, 0, arrayForCount.size() -1);
    }

    private AllLetterCounter(
            List<Future<Map<Character, Long>>> arrayForCount
            , int startPosition
            , int endPosition
    ) {
        this.arrayForCount = arrayForCount;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @SneakyThrows
    @Override
    protected Map<Character, Long> compute() {
        Map<Character, Long> reply;

        if (startPosition > endPosition)
            reply = Collections.emptyMap();

        else if (startPosition == endPosition)
            reply = new HashMap<>(getElemFromArray(startPosition));

        else if (endPosition - startPosition == 1)
            reply = mergeMap(
                    getElemFromArray(startPosition),
                    getElemFromArray(endPosition)
            );

        else
            reply = computeRecursively();
        return reply;
    }

    private Map<Character, Long> computeRecursively() {
        int midPosition = (startPosition + endPosition) / 2;
        AllLetterCounter firstCounter = new AllLetterCounter(arrayForCount, startPosition, midPosition);
        AllLetterCounter secondCounter = new AllLetterCounter(arrayForCount, midPosition + 1, endPosition);
        firstCounter.fork();
        return mergeMap(
                firstCounter.join(),
                secondCounter.compute()
        );
    }

    private Map<Character, Long> mergeMap(
            Map<Character, Long> firstMap,
            Map<Character, Long> seconfMap
    ) {

        LetterCountMerger merger = new LetterCountMergerImpl(
                firstMap,
                seconfMap
        );
        return merger.merge();
    }

    @SneakyThrows
    private Map<Character, Long> getElemFromArray(int index) {
        return arrayForCount.get(index).get();
    }


}
