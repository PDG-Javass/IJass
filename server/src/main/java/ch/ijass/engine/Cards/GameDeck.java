package ch.ijass.engine.Cards;

import java.util.Collections;

public class GameDeck extends Deck {

  /** Constructeur sans paramètre du deck de Jass */
  public GameDeck() {
    super();
    initializeDeck();
  }

  /** Initialise le deck sans les mélanger */
  public void initializeDeck() {
    content = Card.getInitialDeck();
  }

  /** Mélange le deck */
  public void shuffle() {
    Collections.shuffle(content);
  }

  /**
   * Fonction permettant de tirer une carte aléatoirement dans le deck, cette dernière ne se trouve
   *
   * @return la carte choisie, s'il en reste
   */
  public Card pickCardRandomly() {
    Card ret = null;
    shuffle();
    ret = content.get(0);
    content.remove(0);
    if (ret == null) {
      throw new RuntimeException("Could not choose a card from the deck");
    }
    return ret;
  }
}
