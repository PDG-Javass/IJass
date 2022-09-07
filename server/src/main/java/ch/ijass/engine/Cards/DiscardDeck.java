package ch.ijass.engine.Cards;

import java.util.ArrayList;

public class DiscardDeck extends Deck {
  int counterTrump;

  public DiscardDeck() {
    counterTrump = 0;
  }

  public void addFold(ArrayList<Card> cards, CardColor trump) {
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

  public void resetDiscardDeck() {
    counterTrump = 0;
    content.clear();
  }
}
