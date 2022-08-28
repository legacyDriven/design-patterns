package com.epam.rd.autocode.startegy.cards;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TexasHoldemCardDealingStrategyImpl implements CardDealingStrategy {

    private Card[] communityCards;
    private Card[] remaining;
    private Map<String, Card[]> playersCards;

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        return null;
    }

    final CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

//    testCase(strategy, 52,3,
//            "{Community=[45, 44, 43, 42, 41], "+
//            "Player 1=[51, 48], "+
//            "Player 2=[50, 47], "+
//            "Player 3=[49, 46], "+
//            "Remaining=[40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}") {
//
//    }
//
//
//    private void testCase(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
//        final Deck deck = new DeckImpl(deckSize);
//        assertEquals(deckSize, deck.size());
//        assertEquals(
//                expected,
//                new TreeMap(strategy.dealStacks(deck, players)).toString()
//        );
//        assertEquals(0, deck.size());
//    }
}

