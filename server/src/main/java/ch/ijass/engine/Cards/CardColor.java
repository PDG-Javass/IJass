package ch.ijass.engine.Cards;

public enum CardColor {
  DIAMONDS,
  SPADES,
  HEARTS,
  CLUBS;

  private boolean trump;

  public void setTrump(boolean trump) {
    this.trump = trump;
  }
}
