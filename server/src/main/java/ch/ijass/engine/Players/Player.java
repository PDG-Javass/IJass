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

  public Player(String name, Team team) {
    this.name = name;
    this.team = team;
    hand = new Hand();
  }

  public Card playCard(InGameCard playMat, CardColor trump) {
    Card cardToPlay = play(playMat, trump);
    hand.play(cardToPlay);
    return cardToPlay;
  }

  public abstract Card play(InGameCard playMat, CardColor trump);

  public Team getTeam() {
    return team;
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

  public Hand getHand(){
    return hand;
  }

    public String getName() {
        return name;
    }

}
