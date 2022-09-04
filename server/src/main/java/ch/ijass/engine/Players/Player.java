package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.HandDeck;

import java.util.Collection;

public abstract class Player {
  private final String name;
  protected HandDeck hand;
  private Team team;

  private Player(String name, Collection<Card> hand) {
    this.name = name;
    this.hand = new HandDeck();
    this.hand.copyDeck(hand);
  }

  public Player() {
    this.name = "Unnamed";
    this.hand = new HandDeck();
  }

  public Player(String name, Team team) {
    this.name = name;
    this.team = team;
    hand = new HandDeck();
  }

  public Card playCard(BoardDeck playMat, CardColor trump) {
    Card cardToPlay = play(playMat, trump);
    hand.play(cardToPlay);
    return cardToPlay;
  }

  public abstract Card play(BoardDeck playMat, CardColor trump);

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
      card.setOwner(this);
    }
  }

  public void addCard(Card card) {
    hand.addCard(card);
    card.setOwner(this);
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
