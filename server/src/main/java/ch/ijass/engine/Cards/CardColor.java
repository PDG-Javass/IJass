package ch.ijass.engine.Cards;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardColor {

  SPADES,
  CLUBS,
  HEARTS,
  DIAMONDS;

  private boolean trump;

  public void setTrump(boolean trump) {
    this.trump = trump;
  }
  @JsonValue
  public int value() {
    return ordinal();
  }
}
