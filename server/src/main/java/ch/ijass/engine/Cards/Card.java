package ch.ijass.engine.Cards;

import ch.ijass.engine.Players.Player;
import java.util.ArrayList;

/** Une carte. */
public class Card implements Comparable {
  /** La couleur de la carte. */
  private final CardColor color;

  /** La valeur de la carte. */
  private final CardValue value;

  /** L'id du joueur qui possède la carte. */
  private int playerId;

  /**
   * Crée une carte en spécifiant sa couleur et sa valeur.
   *
   * @param c la couleur souhaitée de la carte
   * @param v la valeur souhaitée de la carte
   */
  public Card(CardColor c, CardValue v) {
    color = c;
    value = v;
  }

  /**
   * Construit un deck initial pour le jeu de Jass
   *
   * @return un nouveau vecteur de cartes contenant toutes les cartes du jeu
   */
  public static ArrayList<Card> getInitialDeck() {
    ArrayList<Card> ret = new ArrayList<>();
    for (int i = 0; i < CardColor.values().length; i++) {
      for (int j = 0; j < CardValue.values().length; j++) {
        ret.add(new Card(CardColor.values()[i], CardValue.values()[j]));
      }
    }
    return ret;
  }

  /**
   * @param rank
   * @param color
   * @param trump
   * @return un vecteur de cartes contenant toutes les cartes battant la carte courante de la même
   *     couleur.
   */
  public static ArrayList<Card> getBiggerCards(CardValue rank, CardColor color, boolean trump) {
    ArrayList<Card> ret = new ArrayList<>();

    if (trump) {
      for (int i = rank.ordinalWithTrump() + 1; i < CardValue.valuesWithTrump().size(); i++) {
        ret.add(new Card(color, CardValue.valuesWithTrump().get(i)));
      }
    } else {
      for (int i = rank.ordinal() + 1; i < CardValue.values().length; i++) {
        ret.add(new Card(color, CardValue.values()[i]));
      }
    }
    return ret;
  }

  /**
   * La valeur de la carte.
   *
   * @return
   */
  public CardValue getValue() {
    return value;
  }

  /**
   * @return l'identifiant du propriétaire de la carte
   */
  public int getPlayerId() {
    return playerId;
  }

  /**
   * @return la couleur de la carte
   */
  public CardColor getColor() {
    return color;
  }

  /**
   * Assigne la carte à un identifiant de joueur
   *
   * @param owner
   */
  public void setPlayerId(Player owner) {
    this.playerId = owner.getId();
  }

  /**
   * Indique le nombre de points que vaut la carte sous la couleur d'atout spécifiée
   *
   * @param trump la couleur courante de l'atout
   * @return le nombre de points que vaut la carte
   */
  public int points(CardColor trump) {
    if (color == trump) {
      if (value == CardValue.JACK) return 20;
      else if (value == CardValue.NINE) return 14;
    }
    return value.points();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    switch (value) {
      case SIX:
        sb.append("Six");
        break;
      case SEVEN:
        sb.append("Seven");
        break;
      case EIGHT:
        sb.append("Eight");
        break;
      case NINE:
        sb.append("Nine");
        break;
      case TEN:
        sb.append("Ten");
        break;
      case JACK:
        sb.append("Jack");
        break;
      case QUEEN:
        sb.append("Queen");
        break;
      case KING:
        sb.append("King");
        break;
      case ACE:
        sb.append("Ace");
        break;
    }
    sb.append(" of ");
    switch (color) {
      case CLUBS:
        sb.append("Clubs");
        break;
      case SPADES:
        sb.append("Spades");
        break;
      case HEARTS:
        sb.append("Hearts");
        break;
      case DIAMONDS:
        sb.append("Diamonds");
        break;
    }
    return sb.toString();
  }

  /**
   * Compare la carte courante à une autre carte. La comparaison se fait en fonction de la valeur de
   * la carte et sa couleur.
   *
   * @implNote La comparaison ne se fait pas en fonction de la force de la carte en jeu. Elle permet
   *     uniquement de trier les cartes par couleur puis valeur.
   * @param o the object to be compared.
   * @return
   */
  @Override
  public int compareTo(Object o) {
    if (o.getClass() != Card.class)
      throw new RuntimeException("Card comparison with incompatible class");
    else {
      Card other = (Card) o;
      int colorDiff = color.ordinal() - other.color.ordinal();
      int valueDiff = value.ordinal() - other.value.ordinal();
      if (colorDiff != 0) return colorDiff;
      else return valueDiff;
    }
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != Card.class) return false;
    Card other = (Card) obj;
    return color == other.color && value == other.value;
  }
}
