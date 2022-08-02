package com.epam.rd.autocode.decorator;

import java.util.*;
import java.util.stream.Collectors;

public class Decorators <T> extends ArrayList<T> {

    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        if(sourceList == null) throw new UnsupportedOperationException();
        return sourceList.stream().filter(n-> sourceList.indexOf(n)%2==0).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public T get(int index) {
        return super.get(index);
    }

    public int size(){
        return super.size();
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }
}
