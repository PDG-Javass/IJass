package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.HandDeck;
import java.util.Collection;

public abstract class Player {
  private int id;
  private final String name;
  protected HandDeck hand;
  private Team team;
  private static int counterId = 0;

  public Player(String name, Team team) {
    this.id = ++counterId;
    this.name = name;
    this.team = team;
    hand = new HandDeck();
  }

  public int getId() {
    return id;
  }

  public Card playCard(BoardDeck playMat, CardColor trump) {
    Card cardToPlay = chooseCard(playMat, trump);
    hand.play(cardToPlay);
    return cardToPlay;
  }

  public abstract Card chooseCard(BoardDeck playMat, CardColor trump);

  public Card playChoice(Card choice) { return hand.play(choice); }

  public Card playChoice(int choice) { return hand.play(choice); }

  public Team getTeam() {
    return team;
  }

  public int numberOfCardsInHand() {
    return hand.numberOfCards();
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public abstract CardColor chooseTrump();

  public void setHand(Collection<Card> content) {
    this.hand.emptyDeck();
    this.hand.addCards(content);
    for (Card card : content) {
      card.setPlayerId(this);
    }
  }

  public void addCard(Card card) {
    hand.addCard(card);
    card.setPlayerId(this);
  }

  public void emptyHand() {
    hand.emptyDeck();
  }

  public HandDeck getHand() {
    return hand;
  }

  public String getName() {
    return name;
  }
}
