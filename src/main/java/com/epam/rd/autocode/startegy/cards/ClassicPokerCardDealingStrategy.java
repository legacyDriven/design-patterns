package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassicPokerCardDealingStrategy implements CardDealingStrategy{
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> result = new HashMap<>(getDealtPlayers(deck, players));
        result.put("Remaining", getRemainingCards(deck));
        return result;
    }

    private List<Card> getRemainingCards(Deck deck){
        List<Card> result = new ArrayList<>();
        while(deck.size()>0){
            result.add(deck.dealCard());
        }
        return result;
    }

    private Map<String, List<Card>> getDealtPlayers(Deck deck, int players){
        Map<String, List<Card>> result = new HashMap<>();
        List<Card> toDeal = playersCardsToDeal(deck, players);
        for(int i = 0; i<players; i++){
            result.put("Player " + (i+1), toDeal.stream().filter(n-> toDeal.indexOf(n)%players==0).collect(Collectors.toList()));
            toDeal.remove(0);
        }
        return result;
    }

    private List<Card> playersCardsToDeal(Deck deck, int players){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i<players*5; i++){
            cards.add(deck.dealCard());
        }
        return cards;
    }
}
