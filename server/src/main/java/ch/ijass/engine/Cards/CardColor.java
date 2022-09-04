package ch.ijass.engine.Cards;

public enum CardColor {
  DIAMONDS,
  SPADES,
  HEARTS,
  CLUBS;

  private static CardColor trump;

  public static void setTrump(CardColor trump) {
    CardColor.trump = trump;
  }

  public boolean isTrump() { return this == trump; }
}
