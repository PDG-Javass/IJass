package ch.ijass.engine.Players;

public class Team {
  private int score;

  public int getScore() {
    return score;
  }

  public void addPoints(int points) {
    if (points < 0) throw new RuntimeException("Adding negative points to score");
    score += points;
  }
}
