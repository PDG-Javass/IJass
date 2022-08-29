package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.Hand;

import java.util.Collection;

public abstract class Player {
    private final String name;
    private Hand hand;

    private Player(String name, Collection<? extends Card> hand) {
        this.name = name;
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

    abstract int askCardToPlay();

    public Card play() {
        int index = askCardToPlay(); // TODO: implémenter les méthodes des sous-classes Bot/PersonPlayer
        return hand.play(index);
    }
}
