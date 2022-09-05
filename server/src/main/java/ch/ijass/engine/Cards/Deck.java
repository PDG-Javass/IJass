package ch.ijass.engine.Cards;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class Deck {
  protected Vector<Card> content;

  public Deck() {
    content = new Vector<>();
  }

  public void addCard(Card card) {
    if (card == null) throw new RuntimeException("Adding null card to hand");
    content.add(card);
  }

  public void addCards(Collection<Card> content) {
    this.content.addAll(content);
  }

  public void emptyDeck() {
    content.removeAllElements();
  }




  public int numberOfCards() {
    if (content == null) throw new RuntimeException("Uninitialized deck content");
    return content.size();
  }

  public Vector<Card> getContent() {
    return content;
  }
  /*
  public boolean contains(Card c) {
    for (Card card : content) {
      if (card.isEqual(c)) return true;
    }
    return false;
  }

   */


  public Card play(Card card) {
    if (content.contains(card)) {
      content.remove(card);
      return card;
    }
    throw new RuntimeException("Can not play a card not in the Deck");
  }

  public Vector<Card> getAllCardsOfColor(Vector<Card> cards, CardColor color) {
    Vector<Card> ret = new Vector<>();
    for (Card card : cards) {
      if (card.getColor() == color) ret.add(card);
    }
    return ret;
  }



  public Card getHighestByColor(Vector<Card> cards, CardColor color, boolean trump) {
    Vector<Card> res = getAllCardsOfColor(cards, color);
    if (trump) {
      return Collections.max(res, (c1, c2) -> c1.getValue().ordinalWithTrump() - c2.getValue().ordinalWithTrump());
    } else {
      return Collections.max(res, (c1, c2) -> c1.getValue().ordinal() - c2.getValue().ordinal());
    }
  }

  public Card getLowestByColor(Vector<Card> cards,CardColor color, boolean trump) {
    Vector<Card> res = getAllCardsOfColor(cards, color);
    if (trump) {
      return Collections.min(res, (c1, c2) -> c1.getValue().ordinalWithTrump() - c2.getValue().ordinalWithTrump());
    } else {
      return Collections.min(res, (c1, c2) -> c1.getValue().ordinal() - c2.getValue().ordinal());
    }
  }

}
