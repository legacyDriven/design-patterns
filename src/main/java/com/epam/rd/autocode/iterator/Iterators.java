package com.epam.rd.autocode.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        if (array==null) throw new UnsupportedOperationException();
        return Arrays.stream(array).
                flatMap(value -> IntStream.of(value, value)).
                boxed().iterator();
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        if(array==null) throw new UnsupportedOperationException();
        return Arrays.stream(array).
                flatMap(value -> IntStream.of(value, value, value)).
                boxed().iterator();
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        if(array==null) throw new UnsupportedOperationException();
        return Arrays.stream(array).
                flatMap(value -> IntStream.of(value, value, value, value, value)).
                boxed().iterator();
    }

    public static Iterable<String> table(String[] columns, int[] rows){
        if(columns == null || rows == null) throw new UnsupportedOperationException();

        return Arrays.stream(columns)
            .flatMap(cols -> Arrays.stream(rows)
                    .boxed()
                    .map(row -> cols + row))
            .collect(Collectors.toList());
    }
}
