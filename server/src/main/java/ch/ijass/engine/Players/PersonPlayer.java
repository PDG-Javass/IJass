package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;

public class PersonPlayer extends Player {

  public PersonPlayer(String name, Team team) {
    super(name, team);
  }

  public PersonPlayer() {
    this("No name", new Team());
  }

  @Override
  public Card chooseCard(BoardDeck playMat, CardColor trump) {
    return null;
  }

  @Override
  public boolean isBot() { return false; }

  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  } // todo a modifier
}
