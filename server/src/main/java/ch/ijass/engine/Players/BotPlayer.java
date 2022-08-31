package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.InGameCard;
import java.util.Vector;

public class BotPlayer extends Player {

  public BotPlayer(String name, Team team) { super(name, team); }
  @Override
  public Card play(InGameCard playMat, CardColor trump) {
    Vector<Card> playableCards = hand.getPlayableCard(playMat, trump);

    return hand.getPlayableCard(playMat,trump).firstElement();
    // todo a modifier pour l'instant rend la 1er carte playable

    // A ne pas oublier : doit enlever la carte jouée de sa hand
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
