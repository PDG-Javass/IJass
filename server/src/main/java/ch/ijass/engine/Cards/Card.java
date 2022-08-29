package ch.ijass.engine.Cards;

import ch.ijass.engine.Players.Player;

import java.util.Collections;
import java.util.Vector;

public class Card implements Comparable{
    private final CardColor color;
    private final CardValue value;

    private Player owner;

    /**
     * Constructeur privé de carte
     * @param c la couleur souhaitée de la carte
     * @param v la valeur souhaitée de la carte
     */
    private Card(CardColor c, CardValue v) { color = c; value = v; }

    public Card(Card other) { color = other.color; value = other.value; }
    /**
     * Construit un deck initial pour le jeu de Jass
     * @return un nouveau vecteur de cartes contenant toutes les cartes du jeu
     */
    public static Vector<Card> getInitialDeck() {
        Vector<Card> ret = new Vector<>();
        for (int i = 0; i < CardColor.values().length; i++) {
            for (int j = 0; j < CardValue.values().length; j++) {
                ret.add(new Card(CardColor.values()[i], CardValue.values()[j]));
            }
        }
        return ret;
    }

    /**
     * Indique le nombre de points que vaut la carte sous la couleur d'atout spécifiée
     * @param trump la couleur courante de l'atout
     * @return le nombre de points que vaut la carte
     */
    public int points(CardColor trump) {
        if (color == trump) {
            if (value == CardValue.JACK)
                return 20;
            else if (value == CardValue.NINE)
                return 14;
        }
        return value.points();
    }

    public boolean isEqual(Card other) {
        return color == other.color && value == other.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (value) {
            case SIX -> sb.append("Six");
            case SEVEN -> sb.append("Seven");
            case EIGHT -> sb.append("Eight");
            case NINE -> sb.append("Nine");
            case TEN -> sb.append("Ten");
            case JACK -> sb.append("Jack");
            case QUEEN -> sb.append("Queen");
            case KING -> sb.append("King");
            case ACE -> sb.append("Ace");
        }
        sb.append(" of ");
        switch (color) {
            case CLUBS -> sb.append("Clubs");
            case SPADES -> sb.append("Spades");
            case HEARTS -> sb.append("Hearts");
            case DIAMONDS -> sb.append("Diamonds");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Vector<Card> test = getInitialDeck();
        Collections.shuffle(test);
        System.out.println("Before");
        for (Card c : test) {
            System.out.println(c);
        }
        Collections.sort(test);
        System.out.println("After");
        for (Card c : test) {
            System.out.println(c);
            System.out.print(c.value.points());
            System.out.println(" pts");
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != Card.class)
            throw new RuntimeException("Card comparison with incompatible class");
        else {
            Card other = (Card)o;
            int colorDiff = color.ordinal() - other.color.ordinal();
            int valueDiff = value.ordinal() - other.value.ordinal();
            if (colorDiff != 0)
                return colorDiff;
            else
                return valueDiff;
        }
    }
}
