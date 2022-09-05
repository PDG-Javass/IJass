package ch.ijass.engine.Cards;


public class StartingDeck extends Deck {

  /**
   * Constructeur sans paramètre du deck de Jass
   */
  public StartingDeck() {
    super();
    initializeDeck();
  }

  /**
   * Initialise le deck sans les mélanger
   */
  public void initializeDeck() {
    content = Card.getInitialDeck();
  }

}




