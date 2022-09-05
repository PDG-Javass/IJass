package ch.ijass.engine.Cards;

import java.util.*;

public class HandDeck extends Deck {

  public HandDeck() {
    super();
  }

  public void sort() {
    Collections.sort(content);
  }

  /*
  public Card play(int index) {
    if (index < 0 || index > content.size())
      throw new RuntimeException("Index out of bounds for card played");
    Card ret = content.get(index);
    content.remove(index);
    return ret;
  }
   */



  public int getNumberOfCardsByColor(Vector<Card> cards, CardColor color) {
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
    return Collections.max(
            map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue())
        .getKey();
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
    if (playMat.numberOfCards() == 0) {
      return content;

    } else {

      CardColor color = playMat.colorAsked();

      // si la couleur demandée est atout
      if (color == trump) {
        if (getNumberOfCardsByColor(content, trump) == 0
            || (getNumberOfCardsByColor(content, trump) == 1 && findCard(trump, CardValue.JACK) != null)) {
          return content;
        } else {
          return getAllCardsOfColor(content, trump);
        }
      }

      // si la couleur demandée n'est pas atout
      else {

        Vector<Card> ret = new Vector<>();
        ret.addAll(getAllCardsOfColor(content, color));

        // si personne n'a coupé
        if (!playMat.isCut(trump)) {
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
            }
          }
        }
        return ret.isEmpty() ? content : ret;
      }
    }
  }


  public Card findAce(CardColor trump) {
    for (Card card : content) {
      if (card.getColor() != trump && card.getValue() == CardValue.ACE) {
        return card;
      }
    }
    return null;
  }

  public Card getAdvantageWithoutTrump(BoardDeck board, CardColor trump){
    if ((board.isCut(trump)) || getNumberOfCardsByColor(content, board.colorAsked()) == 0){
      return null;
    }
    else{
      Card highestCardOnBoard = board.getHighestByColor(board.content, board.colorAsked(), false);
      Card highestCardInHand = getHighestByColor(content, board.colorAsked(), false);
      if (highestCardInHand.getValue().ordinal() > highestCardOnBoard.getValue().ordinal()){
          return highestCardInHand;
      }
      else{
          return null;
      }
    }
  }

  // todo AAAAAHHHHH
  public Card findSmallCardInMostPresentColor(Vector<Card> playableCards , CardColor trump){
    CardColor mostPresent = getColorMostPresent();
    if(mostPresent == trump){
      return null;
    }
    return null;
  }



}
