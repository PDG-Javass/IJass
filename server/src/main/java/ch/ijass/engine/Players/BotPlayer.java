package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;

public class BotPlayer extends Player {

  public BotPlayer(String name, Team team) {
    super(name, team);
  }

  @Override
  public Card chooseCard(BoardDeck playMat, CardColor trump) {
    System.out.println(getName() + " : " + hand.getPlayableCard(playMat, trump).get(0));
    Card choice = hand.getPlayableCard(playMat, trump).get(0);
    return choice;
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
