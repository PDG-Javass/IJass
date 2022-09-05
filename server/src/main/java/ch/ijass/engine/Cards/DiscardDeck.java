package ch.ijass.engine.Cards;

import java.util.Vector;

public class DiscardDeck extends Deck {
    int counterTrump;

    public DiscardDeck() {
        counterTrump = 0;
    }

    public void addFold(Vector<Card> cards, CardColor trump) {
        for (Card card : cards) {
            if (card.getColor() == trump) {
                counterTrump++;
            }
        }
        content.addAll(cards);
    }

    public int getCounterTrump() {
        return counterTrump;
    }
}
