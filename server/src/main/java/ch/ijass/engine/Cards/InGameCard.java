package ch.ijass.engine.Cards;

import ch.ijass.engine.Players.Player;

public class InGameCard extends Deck {

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
      return getHighestByColor(trump).getOwner();
    } else {
      return getHighestByColor(colorAsked).getOwner();
    }
  }

  public Card getHighestByColor(CardColor color) {
    Card highestTrump = null;
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
