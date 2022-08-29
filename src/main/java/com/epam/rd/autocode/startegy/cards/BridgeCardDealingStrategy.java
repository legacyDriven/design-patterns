package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BridgeCardDealingStrategy implements CardDealingStrategy{
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        if(deck.size()!=52 || players!=4) throw new UnsupportedOperationException("Deck must be 52 cards, player count must be 4!");
        Map<String, List<Card>> result = initiateDealtMap(players);
        int pointer = 0;
        for (int i = 0; i<52; i++){
            result.get("Player " + (pointer+1)).add(deck.dealCard());
            if(pointer==players-1) {
                pointer = 0;
            } else pointer++;}
        return result;
    }

    private static Map<String, List<Card>> initiateDealtMap(int players){
        Map<String, List<Card>> result = new HashMap<>();
        for(int i = 1; i<players+1; i++){
            result.put("Player " + i, new ArrayList<>());
        }
        return result;
    }
}
