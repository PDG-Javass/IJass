package ch.ijass.engine.Cards;

import java.util.Collections;

public class StartingDeck extends Deck {

  /** Constructeur sans paramètre du deck de Jass */
  public StartingDeck() {
    super();
    initializeDeck();
  }

  /** Initialise le deck sans les mélanger */
  public void initializeDeck() {
    content = Card.getInitialDeck();
  }


  /**
   * Fonction permettant de tirer une carte aléatoirement dans le deck, cette dernière ne se trouve
   *
   * @return la carte choisie, s'il en reste
   */
  public Card pickCardRandomly() {
    Collections.shuffle(content);
    Card ret = content.get(0);;
    content.remove(0);
    if (ret == null) {
      throw new RuntimeException("Could not choose a card from the deck");
    }
    return ret;
  }
}
