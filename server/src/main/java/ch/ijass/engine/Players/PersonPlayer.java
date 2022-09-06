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
  public Card play(BoardDeck playMat, DiscardDeck playedCards, CardColor trump) {
    System.out.println(getName() + " : " + hand.getPlayableCard(playMat, trump).get(0));
    return hand.getPlayableCard(playMat, trump)
        .get(0); // todo a modifier pour l'instant rend la 1er carte de la main

    // A ne pas oublier : doit enlever la carte jouée de sa hand
  }

  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
