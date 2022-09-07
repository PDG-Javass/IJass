package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.DiscardDeck;

public class PersonPlayer extends Player {

  public PersonPlayer(String name, Team team) {
    super(name, team);
  }

  public PersonPlayer() {
    this("No name", new Team());
  }

  @Override
  public Card play(BoardDeck playMat, DiscardDeck playedCards, CardColor trump, int choice) {
    return hand.play(0);
  }

  @Override
  public boolean isBot() { return false; }

  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
