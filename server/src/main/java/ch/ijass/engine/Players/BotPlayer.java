package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.InGameCard;
import java.util.Vector;

public class BotPlayer extends Player {

  @Override
  public Card play(InGameCard playMat, CardColor trump) {
    Vector<Card> playableCards = hand.getPlayableCard(playMat, trump);

    return hand.getPlayableCard(playMat,trump).firstElement();
    // todo a modifier pour l'instant rend la 1er carte playable

    // A ne pas oublié : doit enlever la carte joué de sa hand
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
