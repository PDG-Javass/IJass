package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.InGameCard;

public class PersonPlayer extends Player {

  public PersonPlayer(String name, Team team) { super(name, team); }
  @Override
  public Card play(InGameCard playMat, CardColor trump) {
    return hand.getPlayableCard(playMat, trump).firstElement(); // todo a modifier pour l'instant rend la 1er carte de la main

    // A ne pas oublier : doit enlever la carte jouée de sa hand

  }

  public CardColor chooseTrump() {
    return CardColor.SPADES;
  } // todo a modifier
}
