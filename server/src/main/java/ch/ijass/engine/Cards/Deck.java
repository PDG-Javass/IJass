package ch.ijass.engine.Cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Deck {
  protected ArrayList<Card> content;

  public Deck() {
    content = new ArrayList<>();
  }

  public void addCards(Collection<Card> content) {
    this.content.addAll(content);
  }

  public void emptyDeck() {
    for (Card card : content) {
      card.setOwner(null);
    }
    content.clear();
  }

  public void addCard(Card card) {
    if (card == null) throw new RuntimeException("Adding null card to hand");
    content.add(card);
  }

  public void copyDeck(Collection<? extends Card> deck) {
    if (deck == null) throw new RuntimeException("Copy of a null card collection");
    emptyDeck();
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

  public Card getHighestCard() {
    Card ret = content.get(0);
    for (Card card : content) {
      if (card.isStronger(ret))
        ret = card;
    }
    return ret;
  }

  public Vector<Card> getCardsOfColor(CardColor color) {
    Vector<Card> ret = new Vector<>();
    for (Card c : content) {
      if (c.getColor() == color) ret.add(c);
    }
    return ret;
  }

  public Card play(Card card) {
    if (content.contains(card)) {
      content.remove(card);
      return card;
    }
    throw new RuntimeException("Can not play a card not in the Deck");
  }

  // todo add getHighestCard(Cardcolor color)
  // doit marcher pour atout et non atout
}
