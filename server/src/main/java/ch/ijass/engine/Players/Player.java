package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.Hand;

import java.util.Collection;

public class Player {
    private final String name;
    private Hand hand;

    public Player(String name, Collection<? extends Card> hand) {
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
}
