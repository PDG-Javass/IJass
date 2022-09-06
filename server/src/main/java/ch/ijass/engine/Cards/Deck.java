package ch.ijass.engine.Cards;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collection;
import java.util.ArrayList;

public class Deck {
  protected ArrayList<Card> content;

  public Deck() {
    content = new ArrayList<>();
  }

  public void addCards(Collection<Card> content) {
    this.content.addAll(content);
  }

  public void emptyDeck() {
    content.clear();
  }

  public void addCard(Card card) {
    if (card != null)
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

  public ArrayList<Card> getContent() {
    return new ArrayList<>(content);
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

  public ArrayList<Card> getCardsOfColor(CardColor color) {
    ArrayList<Card> ret = new ArrayList<>();
    for (Card c : content) {
      if (c.getColor() == color) ret.add(c);
    }
    return ret;
  }

  public Card play(Card card) {
    if (card != null && content.contains(card)) {
      content.remove(card);
      return card;
    }
    return null;
  }

  public int points(CardColor trump) {
    int count = 0;
    for (Card card : content) {
      if (card.isEqual(new Card(trump, CardValue.JACK)))
        count += 20;
      else if (card.isEqual(new Card(trump, CardValue.NINE)))
        count += 14;
      else
        count += card.points(trump);
    }
    return count;
  }

  @JsonValue
  public ArrayList<Card> value() { return content; }
}
