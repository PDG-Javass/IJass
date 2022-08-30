package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.Hand;
import ch.ijass.engine.Cards.InGameCard;
import java.util.Collection;

public abstract class Player {
  private final String name;
  protected Hand hand;
  private Team team;

  private Player(String name, Collection<Card> hand) {
    this.name = name;
    this.hand = new Hand();
    this.hand.copyDeck(hand);
  }

  public Player() {
    this.name = "Unnamed";
    this.hand = new Hand();
  }

  public Player(String name) {
    this.name = name;
    hand = new Hand();
  }

  public abstract Card play(InGameCard playMat, CardColor trump);

  public Team getTeam() {
    return team;
  }

  abstract CardColor chooseTrump();

  public void setHand(Collection<Card> content) {
    this.hand.emptyDeck();
    this.hand.initializeDeck(content);
  }

  public void emptyHand() {
    hand.emptyDeck();
  }
}
