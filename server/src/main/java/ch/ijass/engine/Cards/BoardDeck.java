package ch.ijass.engine.Cards;

public class BoardDeck extends Deck {

  public BoardDeck() {
    super();
  }

  public int countPoints(CardColor trump) {
    int result = 0;
    for (Card card : content) {
      result += card.points(trump);
    }
    return result;
  }

  public int getFoldWinner(CardColor colorAsked, CardColor trump) {
    allPlayersPlayed();
    if (isCut(trump)) {
      return getHighestByColor(content, trump, true).getPlayerId();
    } else {
      return getHighestByColor(content, colorAsked, false).getPlayerId();
    }
  }

  private void allPlayersPlayed() {
    if (content.size() != 4) {
      throw new RuntimeException("Not all players played");
    }
  }

  public CardColor colorAsked() {
    return content.get(0).getColor();
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
