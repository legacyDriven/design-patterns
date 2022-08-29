package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy implements CardDealingStrategy {

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        if((deck.size()!= 52 || deck.size()!= 36) && players<2) throw new IllegalArgumentException("Wrong parameters passed");
        Map<String, List<Card>> result = FoolCardDealingStrategy.initiateDealtMap(players);
        int pointer = 0;
        for (int i = 0; i<players*6; i++){
            result.get("Player " + (pointer+1)).add(deck.dealCard());
            if(pointer==players-1) {
                pointer = 0;
            } else pointer++;}
        result.put("Trump card", List.of(deck.dealCard()));
        result.put("Remaining", FoolCardDealingStrategy.getRemainingCards(deck));
        return result;
    }

    private static Map<String, List<Card>> initiateDealtMap(int players){
        Map<String, List<Card>> result = new HashMap<>();
        for(int i = 1; i<players+1; i++){
            result.put("Player " + i, new ArrayList<>());
        }
        return result;
    }

    private static List<Card> getRemainingCards(Deck deck){
        List<Card> remaining = new ArrayList<>();
        int counter = deck.size();
        for(int i = 0; i < counter; i++){
            remaining.add(deck.dealCard());
        }
        return remaining;
    }

    public static void main(String[] args) {
        Deck deck = new DeckImpl(52);
        System.out.println(deck);
        FoolCardDealingStrategy foolCardDealingStrategy = new FoolCardDealingStrategy();
        Map<String, List<Card>> durak = foolCardDealingStrategy.dealStacks(deck, 2);
        System.out.println(durak);
    }
}





/*

private void testCase(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
        final Deck deck = new DeckImpl(deckSize);
        assertEquals(deckSize, deck.size());
        assertEquals(
                expected,
                new TreeMap(strategy.dealStacks(deck, players)).toString()
        );
        assertEquals(0, deck.size());
    }

     final CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();

        testCase(strategy, 52, 3,
                "{Player 1=[51, 48, 45, 42, 39, 36], " +
                        "Player 2=[50, 47, 44, 41, 38, 35], " +
                        "Player 3=[49, 46, 43, 40, 37, 34], " +
                        "Remaining=[32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], " +
                        "Trump card=[33]}");
 */