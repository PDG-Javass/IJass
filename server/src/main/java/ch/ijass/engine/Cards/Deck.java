package ch.ijass.engine.Cards;

import java.util.Collection;
import java.util.Vector;

public class Deck {
  protected Vector<Card> content;

  public void initializeDeck(Collection<Card> content) {
    this.content.addAll(content);
  }

  public void emptyDeck() {
    for (Card card : content) {
      card.setOwner(null);
    }
    content.removeAllElements();
  }

  public void addCard(Card card) {
    if (card == null) throw new RuntimeException("Adding null card to hand");
    content.add(card);
  }

  public void copyDeck(Collection<? extends Card> deck) {
    if (deck == null) throw new RuntimeException("Copy of a null card collection");
    content.removeAllElements();
    content.addAll(deck);
  }

  public int numberOfCards() {
    if (content == null) throw new RuntimeException("Uninitialized deck content");
    return content.size();
  }

  public Vector<Card> getContent() {
    return new Vector<>(content);
  }

  public boolean contains(Card c) {
    for (Card card : content) {
      if (card.isEqual(c)) return true;
    }
    return false;
  }

  public boolean isEqual(Deck other) {
    for (Card c : content) {
      if (!other.contains(c)) return false;
    }
    return true;
  }

  public Vector<Card> getCardsOfColor(CardColor color) {
    Vector<Card> ret = new Vector<>();
    for (Card c : content) {
      if (c.getColor() == color) ret.add(c);
    }
    return ret;
  }
}
