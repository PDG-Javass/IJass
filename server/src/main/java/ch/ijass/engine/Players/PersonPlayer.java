package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.DiscardDeck;

/**
 * Un joueur humain.
 */
public class PersonPlayer extends Player {

  /**
   * Crée un joueur humain en spécifiant son nom et son équipe
   * @param name le nom du joueur
   * @param team l'équipe du joueur
   */
  public PersonPlayer(String name, Team team) {
    super(name, team);
  }

  @Override
  public Card play(BoardDeck playMat, DiscardDeck playedCards, CardColor trump, int choice) {
    return hand.play(choice);
  }

  @Override
  public boolean isBot() {
    return false;
  }

  /**
   * Méthode initialement utilisée pour le bot, mais utilisée lors des première simulations. Le joueur choisit la couleur
   * la plus présente dans sa main.
   * @return la couleur choisie
   */
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }
}
