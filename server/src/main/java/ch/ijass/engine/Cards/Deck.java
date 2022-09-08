package ch.ijass.engine.Cards;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.*;

public class Deck {
  protected ArrayList<Card> content;

  public Deck() {
    content = new ArrayList<>();
  }

  public void addCard(Card card) {
    if (card == null) throw new RuntimeException("Adding null card to hand");
    content.add(card);
  }

  public void addCards(Collection<Card> content) {
    this.content.addAll(content);
  }

  public void emptyDeck() {
    content.clear();
  }

  public int numberOfCards() {
    if (content == null) throw new RuntimeException("Uninitialized deck content");
    return content.size();
  }

  public ArrayList<Card> getContent() {
    return content;
  }

  public Card play(Card card) {
    if (card != null && content.contains(card)) {
      content.remove(card);
      return card;
    }
    throw new RuntimeException("Can not play a card not in the Deck");
  }

  public ArrayList<Card> getAllCardsOfColor(ArrayList<Card> cards, CardColor color) {
    ArrayList<Card> ret = new ArrayList<>();
    for (Card card : cards) {
      if (card.getColor() == color) ret.add(card);
    }
    return ret;
  }

  public Card getHighestByColor(ArrayList<Card> cards, CardColor color, boolean trump) {
    ArrayList<Card> res = getAllCardsOfColor(cards, color);
    if (res.isEmpty()) return null;

    if (trump) {
      return Collections.max(res, Comparator.comparingInt(c -> c.getValue().ordinalWithTrump()));
    } else {
      return Collections.max(res, Comparator.comparingInt(c -> c.getValue().ordinal()));
    }
  }

  public Card getLowestByColor(ArrayList<Card> cards, CardColor color, boolean trump) {
    ArrayList<Card> res = getAllCardsOfColor(cards, color);
    if (res.isEmpty()) return null;

    if (trump) {
      return Collections.min(res, Comparator.comparingInt(c -> c.getValue().ordinalWithTrump()));
    } else {
      return Collections.min(res, Comparator.comparingInt(c -> c.getValue().ordinal()));
    }
  }

  public Card getBockByColor(
      ArrayList<Card> cards, DiscardDeck discard, CardColor colorBock, boolean trump) {
    // On cherche les plus grandes cartes d'une certaine couleur
    Card highestHand = getHighestByColor(cards, colorBock, trump);
    if (highestHand == null) return null;
    // On crée un vecteur de cartes contenant toutes les cartes plus elevé que la highestHand
    ArrayList<Card> biggerCards = Card.getBiggerCards(highestHand.getValue(), colorBock, trump);
    // On cherche si toute les cartes plus eleve que la highestHand sont presente dans discard

    return discard.getContent().containsAll(biggerCards) ? highestHand : null;
  }

  public Card pickCardRandomly() {
    Collections.shuffle(content);
    Card ret = content.get(0);
    content.remove(0);
    if (ret == null) {
      throw new RuntimeException("Could not choose a card from the deck");
    }
    return ret;
  }

  public String toString() {
    return content.toString();
  }

  @JsonValue
  public ArrayList<Card> value() {
    return content;
  }
}
