package com.epam.rd.autocode.startegy.cards;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DeckImpl implements Deck {

    private final Stack<Card> cards;

    public DeckImpl(int size) {
        this.cards = new Stack<>();
        for(int i = 0; i<size; i++){
            cards.push(new CardImpl(i));
        }
    }

    @Override
    public Card dealCard() {
        return cards.size() == 0 ? null : cards.pop();
    }

    @Override
    public List<Card> restCards() {
        List<Card> rest = new LinkedList<>(cards);
        cards.clear();
        return rest;
    }

    @Override
    public int size() {
        return cards.size();
    }
}
