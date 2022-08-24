package IJass;

import java.util.Collections;
import java.util.Vector;

public class CardDeck {
    private Vector<Card> content;

    /**
     * Constructeur sans paramètre du deck de Jass
     */
    public CardDeck() { initializeDeck(); }

    /**
     * Initialise le deck sans les mélanger
     */
    public void initializeDeck() { content = Card.getInitialDeck(); }

    /**
     * Mélange le deck
     */
    public void shuffle() { Collections.shuffle(content); }

    /**
     * Fonction permettant de tirer une carte aléatoirement dans le deck, cette dernière ne se trouv
     * @return la carte choisie, si il en reste
     */
    public Card pickCard() {
        Card ret = null;
        shuffle();
        ret = content.get(0);
        content.remove(0);
        if (ret == null) {
            throw new RuntimeException("Could not choose a card from the deck");
        }
        return ret;
    }

}
