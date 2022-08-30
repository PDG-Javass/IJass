package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.CardColor;

import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.Hand;

public class PersonPlayer extends Player {

    @Override
    public Card play() {
        return hand.getContent().firstElement();    // todo a modifier pour l'instant rend la 1er carte de la main
    }


    public CardColor chooseTrump() {
        return CardColor.SPADES;
    } // todo a modifier
}
