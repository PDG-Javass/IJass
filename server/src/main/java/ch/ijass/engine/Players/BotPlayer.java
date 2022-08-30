package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.CardColor;

import ch.ijass.engine.Cards.Card;

public class BotPlayer extends Player {

    @Override
    public Card play() {
        return hand.getContent().firstElement();    // todo a modifier pour l'instant rend la 1er carte de la main
    }

    @Override
    public CardColor chooseTrump(){
        return hand.getColorMostPresent();
    }
}
