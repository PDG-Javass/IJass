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
    System.out.println(getName() + " : " + hand.getPlayableCard(playMat, trump).firstElement());
    Card choice = hand.getPlayableCard(playMat, trump).firstElement();
    playChoice(choice);
    return choice;
    // todo a modifier pour l'instant rend la 1er carte playable

    // A ne pas oublier : doit enlever la carte jouée de sa hand
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
