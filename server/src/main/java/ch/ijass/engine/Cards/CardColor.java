package ch.ijass.engine.Cards;

import com.fasterxml.jackson.annotation.JsonValue;

/** Une couleur de carte. */
public enum CardColor {
  SPADES,
  CLUBS,
  HEARTS,
  DIAMONDS;

  /**
   * Retourne la valeur utilisée dans le JSON communiqué au client
   *
   * @return l'ordinal de la couleur
   */
  @JsonValue
  public int value() {
    return ordinal();
  }
}
