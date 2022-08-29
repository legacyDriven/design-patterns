package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class TexasHoldemCardDealingStrategy implements CardDealingStrategy {

    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        if((deck.size()!= 52 || deck.size()!= 36) && players<2) throw new IllegalArgumentException("Wrong parameters passed");
        List<Card> playersCards = getPlayersCardsToDeal(deck, players);
        Map<String, List<Card>> result = new HashMap<>();
        result.put("Community", getCards(5, deck));
        result.putAll(getPlayers(playersCards, players));
        result.put("Remaining", getCards(deck.size(), deck));
        return result;
    }

    private Map<String, List<Card>> getPlayers(List<Card> playerCards, int players){
        Map<String, List<Card>> result = new HashMap<>();
        for(int i = 0; i<players; i++){
            List<Card> values = new ArrayList<>();
            values.add(playerCards.get(i));
            values.add(playerCards.get(i+players));
            result.put("Player " + (i+1), values);
        }
        return result;
    }

    private List<Card> getPlayersCardsToDeal(Deck deck, int players){
        List<Card> result = new LinkedList<>();
        for(int i = 0; i<players*2; i++){
            result.add(deck.dealCard());
        }
        return result;
    }

    private List<Card> getCards(int number, Deck deck){
        List<Card> result = new ArrayList<>();
        for(int i = 0; i<number; i++){
            result.add(deck.dealCard());
        }
        return result;
    }

    private Map<String, List<Card>> initiateDealtMap(int players){
        Map<String, List<Card>> result = new HashMap<>();
        for(int i = 1; i<players+1; i++){
            result.put("Player " + i, new ArrayList<>());
        }
        return result;
    }
}

