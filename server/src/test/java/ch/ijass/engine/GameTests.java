package ch.ijass.engine;

import ch.ijass.engine.Cards.*;
import ch.ijass.engine.Cards.Card;
import java.util.Random;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Card.class, Deck.class, GameDeck.class, Hand.class})
public class GameTests {
  @Test
  void creatingGameDeck() {
    GameDeck d1 = new GameDeck();
    GameDeck d2 = new GameDeck();

    Vector<Card> content = d1.getContent();
    Card c1 = content.get(0);
    int count = 1;
    for (int i = 1; i < content.size(); i++) {
      count++;
      // Vérifie qu'il n'y ait aucune carte à double
      assert (!c1.isEqual(content.get(i)));
      c1 = content.get(i);
    }
    // Vérifie que les deux decks possèdent toutes les cartes
    assert (count == d2.numberOfCards() && count == 36);
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
      int index = r.nextInt(0, 36); // TODO: ne pas mettre le nombre de cartes en dur ?
      if (!cards.get(index).isEqual(cardsShuffled.get(index))) essaisReussis++;
    }
    assert ((float) essaisReussis / ESSAIS > 0.95);
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
    assert (h1.numberOfCards() == h2.numberOfCards()
        && h2.numberOfCards() == h3.numberOfCards()
        && h3.numberOfCards() == h4.numberOfCards());

    // Vérifie qu'aucune carte n'a été distribuée à double
    for (Card c : h1.getContent()) {
      assert (!h2.contains(c));
      assert (!h3.contains(c));
      assert (!h4.contains(c));
    }

    for (Card c : h2.getContent()) {
      assert (!h3.contains(c));
      assert (!h4.contains(c));
    }

    for (Card c : h3.getContent()) {
      assert (!h4.contains(c));
    }
  }

  @Test
  void CardsManipulations() {
    Hand h1 = new Hand();
    GameDeck gd = new GameDeck();

    for (int i = 0; i < 10; i++) {
      h1.addCard(gd.pickCardRandomly());
    }

    h1.sort();

    // Vérification de l'ordre deux à deux
    Vector<Card> content = h1.getContent();
    Card c = content.get(0);
    for (int i = 1; i < content.size(); i++) {
      assert (c.getColor() != content.get(i).getColor()
          || c.getValue().ordinal() < content.get(i).getValue().ordinal());
      c = content.get(i);
    }

    content = h1.getCardsOfColor(CardColor.DIAMONDS);
    for (Card ca : content) assert (ca.getColor() == CardColor.DIAMONDS);
  }
}
