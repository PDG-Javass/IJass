package ch.ijass.engine;

import ch.ijass.engine.Cards.*;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Players.PersonPlayer;
import ch.ijass.engine.Players.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Card.class, Deck.class, StartingDeck.class, HandDeck.class})
public class GameTests {

  @Test
  public void cardColorTrump() {
    CardColor diamonds = CardColor.DIAMONDS;
    CardColor clubs = CardColor.CLUBS;
    CardColor spades = CardColor.SPADES;
    CardColor hearts = CardColor.HEARTS;

    CardColor.setTrump(CardColor.DIAMONDS);

    assert(diamonds.isTrump() && !clubs.isTrump() && !spades.isTrump() && !hearts.isTrump());

    CardColor.setTrump(CardColor.CLUBS);

    assert(!diamonds.isTrump() && clubs.isTrump() && !spades.isTrump() && !hearts.isTrump());
  }
  @Test
  void creatingGameDeck() {
    StartingDeck d1 = new StartingDeck();
    StartingDeck d2 = new StartingDeck();

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
      StartingDeck d = new StartingDeck();
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
  public void cardOrder() {
    BoardDeck bd = new BoardDeck();
    bd.addCard(new Card(CardColor.HEARTS, CardValue.TEN));
    assert(bd.getHighestCard().isEqual(new Card(CardColor.HEARTS, CardValue.TEN)));
    bd.addCard(new Card(CardColor.SPADES, CardValue.TEN));
    assert(bd.getHighestCard().isEqual(new Card(CardColor.HEARTS, CardValue.TEN)));
    CardColor.setTrump(CardColor.SPADES);
    assert(bd.getHighestCard().isEqual(new Card(CardColor.SPADES, CardValue.TEN)));
    bd.addCard(new Card(CardColor.SPADES, CardValue.NINE));
    assert(bd.getHighestCard().isEqual(new Card(CardColor.SPADES, CardValue.NINE)));
    bd.addCard(new Card(CardColor.SPADES, CardValue.JACK));
    bd.addCard(new Card(CardColor.SPADES, CardValue.ACE));
    assert(bd.getHighestCard().isEqual(new Card(CardColor.SPADES, CardValue.JACK)));
    CardColor.setTrump(CardColor.CLUBS);
    assert(bd.getHighestCard().isEqual(new Card(CardColor.HEARTS, CardValue.TEN)));
  }

  @Test
  void cardsDistribution() {
    StartingDeck gd = new StartingDeck();
    HandDeck h1 = new HandDeck(), h2 = new HandDeck(), h3 = new HandDeck(), h4 = new HandDeck();

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
    HandDeck h1 = new HandDeck();
    StartingDeck gd = new StartingDeck();

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

  @Test
  public void getHighestCard() {
    HandDeck hd = new HandDeck();
    ArrayList<Card> content = new ArrayList<>();
    content.add(new Card(CardColor.SPADES, CardValue.TEN));
    content.add(new Card(CardColor.HEARTS, CardValue.JACK));
    content.add(new Card(CardColor.HEARTS, CardValue.QUEEN));
    content.add(new Card(CardColor.HEARTS, CardValue.SIX));
    content.add(new Card(CardColor.CLUBS, CardValue.NINE));
    content.add(new Card(CardColor.HEARTS, CardValue.JACK));
  }

  @Test
  void findFirstPersonForFirstRound() {
    final int NTESTS = 1000;
    for (int i = 0; i < NTESTS; i++) {
      GameManager gm = new GameManager();
      gm.initiateRound();
      assert (gm.find7ofDiamonds()
          .getHand()
          .contains(new Card(CardColor.DIAMONDS, CardValue.SEVEN)));
    }
  }

  @Test
  void foldTests() {
    GameManager gm = new GameManager();
    int nCards = 9;
    gm.initiateRound();

    // Vérifie que les joueurs jouent bien une carte à chaque plie
    for (int i = 0; i < 9; i++) {
      for (Player player : gm.getPlayers()) {
        assert (player.numberOfCardsInHand() == nCards);
      }
      gm.doOneFold();
      nCards--;
    }
  }

  @Test
  void getWinnerTests() {

    PersonPlayer p1 = new PersonPlayer(),
        p2 = new PersonPlayer(),
        p3 = new PersonPlayer(),
        p4 = new PersonPlayer();

    BoardDeck igc = new BoardDeck();
    Card c1 = new Card(CardColor.CLUBS, CardValue.SIX),
        c2 = new Card(CardColor.CLUBS, CardValue.EIGHT),
        c3 = new Card(CardColor.DIAMONDS, CardValue.EIGHT),
        c4 = new Card(CardColor.DIAMONDS, CardValue.NINE);

    c1.setOwner(p1);
    c2.setOwner(p2);
    c3.setOwner(p3);
    c4.setOwner(p4);

    igc.addCard(c1);
    igc.addCard(c2);
    igc.addCard(c3);
    igc.addCard(c4);

    assert (p2 == igc.getFoldWinner(CardColor.CLUBS, CardColor.CLUBS));
    /*
       c3.setOwner(p1); c1.setOwner(p3);

       assert(p3 == igc.getFoldWinner(CardColor.SPADES, CardColor.SPADES));

       igc.emptyDeck();
       c1 = new Card(CardColor.CLUBS, CardValue.ACE); c2 = new Card(CardColor.HEARTS, CardValue.QUEEN); c4 = new Card(CardColor.HEARTS, CardValue.TEN);

       c1.setOwner(p1); c2.setOwner(p2); c3.setOwner(p3); c4.setOwner(p4);
       igc.addCard(c1); igc.addCard(c2); igc.addCard(c3); igc.addCard(c4);

       assert(p2 == igc.getFoldWinner(CardColor.HEARTS, CardColor.DIAMONDS));
       assert(p1 == igc.getFoldWinner(CardColor.CLUBS, CardColor.SPADES));
    */
  }

  @Test
  void roundTests() {
    GameManager gm = new GameManager();
  }
}
