package ch.ijass.engine.Cards;

import ch.ijass.engine.Players.Player;

public class InGameCard extends Deck {

  public InGameCard() { super(); }
  public int countPoints(CardColor trump) {
    allPlayersPlayed();
    int result = 0;
    for (Card card : content) {
      result += card.points(trump);
    }
    return result;
  }

  public Player getFoldWinner(CardColor colorAsked, CardColor trump) {
    allPlayersPlayed();

    if (isCut(trump)) {
      return getHighestByColor(trump, true).getOwner();
    } else {
      return getHighestByColor(colorAsked, false).getOwner();
    }
  }

  public Card getHighestByColor(CardColor color, boolean trump) {
    Card highestTrump = null;
    if (trump) {
      for (Card card : content) {
        if(card.getColor() == color){
          if(card.getValue() == CardValue.JACK){
            return card;
          }
        }
        if (card.getValue() == CardValue.JACK || card.getValue() == CardValue.NINE) // todo fix this
          return card;
      }
    }
    for (Card card : content) {
      if (card.getColor() == color) {
        if (highestTrump == null) {
          highestTrump = card;
        } else {
          if (card.getValue().ordinal() > highestTrump.getValue().ordinal()) {
            highestTrump = card;
          }
        }
      }
    }
    return highestTrump;
  }

  private void allPlayersPlayed() {
    if (content.size() != 4) {
      throw new RuntimeException("Not all players played");
    }
  }

  public int size() {
    return content.size();
  }

  public CardColor colorAsked() {
    return content.firstElement().getColor();
  }

  public boolean isCut(CardColor trump) {
    for (Card card : content) {
      if (card.getColor() == trump) {
        return true;
      }
    }
    return false;
  }
}
