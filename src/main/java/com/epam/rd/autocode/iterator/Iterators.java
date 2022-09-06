package com.epam.rd.autocode.iterator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<Integer>() {
            final List<Integer> toIterate = Arrays.stream(array).boxed().collect(Collectors.toList());
            int repetition = 1;
            Integer value = null;
            int index = 0;

            @Override
            public boolean hasNext() {
                if(toIterate.size()>index) return true;
                else return false;
            }

            @Override
            public Integer next() {
                if(!hasNext()) throw new NoSuchElementException();
                if(repetition == 1){
                    value=toIterate.get(index);
                    repetition++;
                    return value;
                } else if(repetition == 2){
                    index++;
                    repetition=1;
                    return value;
                }
                return null;
            }
        };
    }


    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {

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
