package ch.ijass.engine.Cards;

import java.util.*;

public class Hand extends Deck {

    public Hand() { content = new Vector<>(); }

    public void sort() { Collections.sort(content); }

    public Card play(int index) {
        if (index < 0 || index > content.size())
            throw new RuntimeException("Index out of bounds for card played");
        Card ret = content.get(index);
        content.remove(index);
        return ret;
    }

    public CardColor getColorMostPresent(CardColor color){
        int nbHearts = 0;
        int nbDiamons = 0;
        int nbSpades = 0;
        int nbClubs = 0;

        for( Card card : content) {
            switch (card.getColor()) {
                case HEARTS : nbHearts++;
                case DIAMONDS : nbDiamons++;
                case SPADES : nbSpades++;
                case CLUBS : nbClubs++;
            }
        }

        Map<CardColor, Integer> map = Map.of(CardColor.HEARTS, nbHearts, CardColor.DIAMONDS, nbDiamons);

        int max = Collections.max(Arrays.asList(nbHearts, nbDiamons, nbSpades, nbClubs));



        switch (max){
            /*
            case nbHearts : return CardColor.HEARTS;
            case nbDiamons : return CardColor.DIAMONDS;
            case nbSpades : return CardColor.SPADES;
            case nbClubs : return CardColor.CLUBS;*/
        }
        return CardColor.HEARTS;
    }


}
