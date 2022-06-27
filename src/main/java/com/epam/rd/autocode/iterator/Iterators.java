package com.epam.rd.autocode.iterator;

import java.util.Iterator;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        if (array==null) throw new UnsupportedOperationException();
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Integer next() {
                return null;
            }
        };
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        throw new UnsupportedOperationException();
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        throw new UnsupportedOperationException();
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        throw new UnsupportedOperationException();
    }



}
