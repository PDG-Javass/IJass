package ch.ijass.engine.Cards;

public class BoardDeck extends Deck {

  /**
   * Constructeur de la classe BoardDeck
   */
  public BoardDeck() {
    super();
  }

  /**
   * Calculer le nombre de points de la plie
   * @param trump la couleur atout
   * @return le nombre de points de la plie
   */
  public int countPoints(CardColor trump) {
    int result = 0;
    for (Card card : content) {
      result += card.points(trump);
    }
    return result;
  }

  /**
   * Trouver le joueur qui a gagné la plie
   * @param colorAsked la couleur demandée
   * @param trump la couleur d'atout
   * @return l'id du joueur qui a gagné la plie
   */
  public int getFoldWinner(CardColor colorAsked, CardColor trump) {
    if (content.size() != 4) {
      throw new RuntimeException("Not all players played");
    }
    if (isCut(trump)) {
      return getHighestByColor(content, trump, true).getPlayerId();
    } else {
      return getHighestByColor(content, colorAsked, false).getPlayerId();
    }
  }

  /**
   * Trouver la couleur de la première carte de la plie
   * @return la couleur de la première carte de la plie
   */
  public CardColor colorAsked() {
    return content.firstElement().getColor();
  }

  /**
   * Vérifier si la plie est coupée
   * @param trump la couleur d'atout
   * @return true si la plie est coupée, false sinon
   */
  public boolean isCut(CardColor trump) {
    for (Card card : content) {
      if (card.getColor() == trump) {
        return true;
      }
    }
    return false;
  }
}
