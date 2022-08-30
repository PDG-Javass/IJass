package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.InGameCard;
import java.util.Vector;

public class BotPlayer extends Player {

  @Override
  public Card play(InGameCard playMat, CardColor trump) {
    Vector<Card> playableCards = hand.getPlayableCard(playMat, trump);

    return playableCards
        .firstElement(); // todo a modifier pour l'instant rend la 1er carte playable
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
