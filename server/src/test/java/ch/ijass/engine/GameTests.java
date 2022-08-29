package ch.ijass.engine;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.Vector;

@SpringBootTest
public class GameTests {
    @Test
    void creatingGameDeck() {
        GameDeck d1 = new GameDeck();
        GameDeck d2 = new GameDeck();

        Card c1 = null;
        int count = 0;
        for (Card c : d1.getContent()) {
            count++;
            if (c1 == null)
                c1 = c;
            else {
                // Vérifie qu'il n'y ait aucune carte à double
                assert(!c1.isEqual(c));
                c1 = c;
            }
        }
        // Vérifie que les deux decks possèdent toutes les cartes
        assert(count == d2.numberOfCards() && count == 36);
    }

    @Test
    void shuffleDeck() {
        int ESSAIS = 1000;
        int essaisReussis = 0;
        for (int i = 0; i < ESSAIS; i++) {
            GameDeck d = new GameDeck();
            Vector<Card> cards = d.getContent();
            d.shuffle();
            Vector<Card> cardsShuffled = d.getContent();
            // Vérifie si le paquet a bien été mélangé en interrogeant le même index de leur contenu
            Random r = new Random();
            int index = r.nextInt(0, 36);
            if (!cards.get(index).isEqual(cardsShuffled.get(index)))
                essaisReussis++;
        }
        assert((float)essaisReussis/ESSAIS > 0.95);
    }

    @Test
    void cardsDistribution() {
        GameDeck gd = new GameDeck();
        Hand h1 = new Hand(), h2 = new Hand(), h3 = new Hand(), h4 = new Hand();

        while (gd.numberOfCards() > 0) {
            h1.addCard(gd.pickCardRandomly());
            h2.addCard(gd.pickCardRandomly());
            h3.addCard(gd.pickCardRandomly());
            h4.addCard(gd.pickCardRandomly());
        }

        // Vérifie que le tas est distribué équitablement
        assert(h1.numberOfCards() == h2.numberOfCards() && h2.numberOfCards() == h3.numberOfCards()
               && h3.numberOfCards() == h4.numberOfCards());

        // Vérifie qu'aucune carte n'ai été distribué à double
        for (Card c : h1.getContent()) {
            assert(h2.contains(c));
            assert(h3.contains(c));
            assert(h4.contains(c));
        }

        for (Card c : h2.getContent()) {
            assert(h3.contains(c));
            assert(h4.contains(c));
        }

        for (Card c : h3.getContent()) {
            assert(h4.contains(c));
        }
    }
}
