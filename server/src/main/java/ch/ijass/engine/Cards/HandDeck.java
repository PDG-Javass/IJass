package ch.ijass.engine.Cards;

import java.util.*;

public class HandDeck extends Deck {

  public HandDeck() {
    super();
  }

  public void sort() {
    Collections.sort(content);
  }

  public Card play(int index) {
    if (index < 0 || index > content.size())
      throw new RuntimeException("Index out of bounds for card played");
    Card ret = content.get(index);
    content.remove(index);
    return ret;
  }

  public Vector<Card> getAllCardOfColor(CardColor color) {
    Vector<Card> ret = new Vector<>();
    for (Card card : content) {
      if (card.getColor() == color) ret.add(card);
    }
    return ret;
  }

  public int getNumberOfCardsByColor(CardColor color) {
    int count = 0;
    for (Card c : content) {
      if (c.getColor() == color) count++;
    }
    return count;
  }

  public CardColor getColorMostPresent() {
    int nbHearts = 0;
    int nbDiamons = 0;
    int nbSpades = 0;
    int nbClubs = 0;

    for (Card card : content) {
      switch (card.getColor()) {
        case HEARTS:
          nbHearts++;
          break;
        case DIAMONDS:
          nbDiamons++;
          break;
        case SPADES:
          nbSpades++;
          break;
        case CLUBS:
          nbClubs++;
          break;
      }
    }

    Map<CardColor, Integer> map =
        Map.of(
            CardColor.HEARTS,
            nbHearts,
            CardColor.DIAMONDS,
            nbDiamons,
            CardColor.SPADES,
            nbSpades,
            CardColor.CLUBS,
            nbClubs);
    return Collections.max(
            map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue())
        .getKey();
  }

  public void throwCard(Card card) {
    content.removeElement(card);
  }

  public Card findCard(CardColor color, CardValue value) {
    for (Card card : content) {
      if (card.getColor() == color && card.getValue() == value) {
        return card;
      }
    }
    return null;
  }

  public Vector<Card> getPlayableCard(BoardDeck playMat, CardColor trump) {

    // 1er joue nimporte quoi
    if (playMat.size() == 0) {
      return content;

    } else {

      CardColor color = playMat.colorAsked();

      // si la couleur demandée est atout
      if (color == trump) {
        if (getNumberOfCardsByColor(trump) == 0
            || (getNumberOfCardsByColor(trump) == 1 && findCard(trump, CardValue.JACK) != null)) {
          return content;
        } else {
          return getAllCardOfColor(trump);
        }
      }

      // si la couleur demandée n'est pas atout
      else {

        Vector<Card> ret = new Vector<>();
        ret.addAll(getAllCardOfColor(color));

        // si personne n'a coupé
        if (!playMat.isCut(trump)) {
          ret.addAll(getAllCardOfColor(trump));

        } else {

          // trouver la carte atout la plus elevée sur le tapis
          Card highestTrump = playMat.getHighestByColor(trump, true);
          // construire un vecteur avec les cartes atouts plus elevées que la carte atout la plus
          // elevée
          for (Card card : content) {
            if (card.getColor() == trump) {
              if (card.getValue().ordinalWithTrump() > highestTrump.getValue().ordinalWithTrump()) {
                ret.add(card);
              }
            }
          }
        }
        return ret.isEmpty() ? content : ret;
      }
    }
  }
}
