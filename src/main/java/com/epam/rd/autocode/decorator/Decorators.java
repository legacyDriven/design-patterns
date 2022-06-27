package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Decorators {

    private List<String> data = Collections.synchronizedList(new ArrayList<>());


    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        if(sourceList == null) throw new UnsupportedOperationException();
        return sourceList.stream().filter(n-> sourceList.indexOf(n)%2==0).collect(Collectors.toUnmodifiableList());

        /*
        class DecoratedList extends List<T> same as XList UTP

        Decorator
Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these
 objects inside special wrapper objects that contain the behaviors.

Implement com.epam.rd.autocode.decorator.Decorators method:

evenIndexElementsSubList - returns a decorator, that manages only the elements with even indices in a source list.
 The decorated list should support the "read" methods: Decorated list should support "read" methods: get(), size(), iterator().
         */
    }
}
