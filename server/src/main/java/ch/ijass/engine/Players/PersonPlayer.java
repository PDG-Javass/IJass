package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.BoardDeck;

public class PersonPlayer extends Player {

  public PersonPlayer(String name, Team team) {
    super(name, team);
  }

  public PersonPlayer() {
    this("No name", new Team());
  }

  @Override
  public Card play(BoardDeck playMat, CardColor trump) {
    System.out.println(getName() + " : " + hand.getPlayableCard(playMat, trump).firstElement());
    return hand.getPlayableCard(playMat, trump)
        .firstElement(); // todo a modifier pour l'instant rend la 1er carte de la main

    // A ne pas oublier : doit enlever la carte jouée de sa hand
  }



  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  } // todo a modifier
}
