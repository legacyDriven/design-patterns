package com.epam.rd.autocode.startegy.cards;

public class CardImpl implements Card {

    private final String name;

    public CardImpl(int name) {
        this.name = Integer.toString(name);
    }

    public CardImpl(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    public String toString() {
        return name;
    }

}
