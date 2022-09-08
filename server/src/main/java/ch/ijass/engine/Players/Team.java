package ch.ijass.engine.Players;

/**
 * Une équipe.
 */
public class Team {

  /**
   * Le score de l'équipe.
   */
  private int score;

  /**
   * @return le score de l'équipe
   */
  public int getScore() {
    return score;
  }

  /**
   * Ajoute un nombre de points au score de l'équipe.
   * @param points le nombre de points à ajouter
   */
  public void addPoints(int points) {
    if (points < 0) throw new RuntimeException("Adding negative points to score");
    score += points;
  }
}
