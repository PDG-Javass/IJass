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

  public ArrayList<Card> getAllCardOfColor(CardColor color) {
    ArrayList<Card> ret = new ArrayList<>();
    for (Card card : content) {
      if (card.getColor() == color) ret.add(card);
    }
    return ret;
  }

  public int getNumberOfCardsByColor(ArrayList<Card> cards, CardColor color) {
    return getAllCardsOfColor(cards, color).size();
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
    return Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
  }

  public Card findCard(CardColor color, CardValue value) {
    for (Card card : content) {
      if (card.getColor() == color && card.getValue() == value) {
        return card;
      }
    }
    return null;
  }

  public ArrayList<Card> getPlayableCard(BoardDeck playMat, CardColor trump) {

    // Premier joueur joue n'importe quoi
    if (playMat.numberOfCards() == 0) {
      return content;

    } else {

      CardColor color = playMat.colorAsked();

      // si la couleur demandée est atout
      if (color == trump) {
        if (getNumberOfCardsByColor(content, trump) == 0
            || (getNumberOfCardsByColor(content, trump) == 1
                && findCard(trump, CardValue.JACK) != null)) {
          return content;
        } else {
          return getAllCardsOfColor(content, trump);
        }
      }

      // si la couleur demandée n'est pas atout
      else {

        ArrayList<Card> ret = new ArrayList<>();
        ArrayList<Card> cardsOfAskedColor = getAllCardsOfColor(content, color);
        ret.addAll(cardsOfAskedColor);

        // si personne n'a coupé
        if (!playMat.isCut(trump)) {
          if(cardsOfAskedColor.isEmpty()) {
            return content;
          }
          ret.addAll(getAllCardsOfColor(content, trump));
        } else {

          // trouver la carte atout la plus elevée sur le tapis
          Card highestTrump = playMat.getHighestByColor(playMat.content, trump, true);
          // construire un vecteur avec les cartes atouts plus elevées que la carte atout la plus
          // elevée
          for (Card card : content) {
            if (card.getColor() == trump) {
              if (card.getValue().ordinalWithTrump() > highestTrump.getValue().ordinalWithTrump()) {
                ret.add(card);
              }
            } else if(cardsOfAskedColor.isEmpty()) {
              ret.add(card);
            }
          }
        }

        return ret.isEmpty() ? content : ret;
      }
    }
  }

  public Card findBock(ArrayList<Card> playable, CardColor trump, DiscardDeck discard) {
    Card bock = null;
    for (CardColor color : CardColor.values()) {
      if (color != trump) {
        bock = getBockByColor(playable, discard, color, false);
        if (bock != null) {
          return bock;
        }
      }
    }
    return null;
  }

  /*public Card findAce(CardColor trump) {
    for (Card card : content) {
      if (card.getColor() != trump && card.getValue() == CardValue.ACE) {
        return card;
      }
    }
    return null;
  }

   */

  public Card getAdvantageWithoutCut(BoardDeck board, CardColor trump) {
  // On regarde si on peut prendre l'avantage sans couper
    if ((!board.isCut(trump) || board.colorAsked() == trump) && getNumberOfCardsByColor(content, board.colorAsked()) != 0) {

      // La couleur demandée n'est pas atout
      if (board.colorAsked() != trump) {
        Card highestCardOnBoard = board.getHighestByColor(board.content, board.colorAsked(), false);
        Card highestCardInHand = getHighestByColor(content, board.colorAsked(), false);
        if (highestCardInHand.getValue().ordinal() > highestCardOnBoard.getValue().ordinal()) return highestCardInHand;

      // La couleur demandée est atout
      } else {
        Card highestCardOnBoard = board.getHighestByColor(board.content, trump, true);
        Card highestCardInHand = getHighestByColor(content, trump, true);
        if (highestCardInHand.getValue().ordinalWithTrump() > highestCardOnBoard.getValue().ordinalWithTrump()) return highestCardInHand;
      }
    }
    return null;
  }


}
