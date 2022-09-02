package ch.ijass.engine.Cards;

public class BoardDeck extends Deck {

  public BoardDeck() {
    super();
  }

  public int countPoints(CardColor trump) {
    allPlayersPlayed();
    int result = 0;
    for (Card card : content) {
      result += card.points(trump);
    }
    return result;
  }

  public int getFoldWinner(CardColor colorAsked, CardColor trump) {
    allPlayersPlayed();
    if (isCut(trump)) {
      return getHighestByColor(trump, true).getPlayerId();
    } else {
      return getHighestByColor(colorAsked, colorAsked == trump).getPlayerId();
    }
  }

  public Card getHighestByColor(CardColor color, boolean trump) {
    Card highestCard = null;

    if (trump) {
      for (Card card : content) {
        if (card.getColor() == color) {
          if (highestCard == null) {
            highestCard = card;
          } else {
            if (card.getValue().ordinalWithTrump() > highestCard.getValue().ordinalWithTrump()) {
              highestCard = card;
            }
          }
        }
      }
    } else {
      for (Card card : content) {
        if (card.getColor() == color) {
          if (highestCard == null) {
            highestCard = card;
          } else {
            if (card.getValue().ordinal() > highestCard.getValue().ordinal()) {
              highestCard = card;
            }
          }
        }
      }
    }
    return highestCard;
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
