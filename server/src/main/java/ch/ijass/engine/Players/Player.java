package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.Hand;

import java.util.Collection;

public abstract class Player {
    private final String name;
    private Hand hand;
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

    public Team getTeam() {
        return team;
    }

    abstract int askCardToPlay();

    abstract CardColor chooseTrump();

    public Card play() {
        int index = askCardToPlay(); // TODO: implémenter les méthodes des sous-classes Bot/PersonPlayer
        return hand.play(index);
    }

    public void setHand(Collection<Card> content) {
        this.hand.emptyDeck();
        this.hand.initializeDeck(content);
    }

    public void emptyHand() { hand.emptyDeck(); }

}
