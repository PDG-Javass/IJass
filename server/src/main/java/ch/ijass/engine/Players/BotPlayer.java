package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.DiscardDeck;

import java.util.Vector;

public class BotPlayer extends Player {

  public BotPlayer(String name, Team team) {
    super(name, team);
  }

  @Override
  public Card play(BoardDeck board, DiscardDeck playedCards, CardColor trump) {
    Vector<Card> playableCards = hand.getPlayableCard(board, trump);

    // on a une seule carte jouable
    if( playableCards.size() == 1){
      return playableCards.get(0);
    }
    else {
      int nbTrumpsHand = hand.getNumberOfCardsByColor(hand.getContent(), trump);

      // On est le premier à jouer
      if(board.numberOfCards() == 0) {

        // Cas 1 : On tire atout avec le meilleur atout
        int nbTrumpsNature = nbTrumpInNature(playedCards, trump);
        if (nbTrumpsHand > 2 && nbTrumpsNature > 0 ||
                (nbTrumpsHand == 2 && nbTrumpsNature > 0 && nbTrumpsNature < 4)) {
          return hand.getHighestByColor(playableCards, trump, true);
        }

        // Cas 2 : On joue un as
        Card ace = hand.findAce(trump);
        if (ace != null) {
          return ace;
        }

        // Cas 3 : On joue une petite carte
        return playableCards.get(0);
      }


      // On est pas le premier à jouer
      else{

        // Cas 1 : On peut prendre l'avantage sans jouer d'atout
        Card winCard = hand.getAdvantageWithoutTrump(board, trump);
        if( winCard != null){
          return winCard;
        }

        // Cas 2 : On coupe car il y a beaucoup de points à gagner
        if ((board.countPoints(trump) > 10) && hand.getNumberOfCardsByColor(playableCards, trump) > 0) {
          return hand.getLowestByColor(playableCards, trump, true);
        }


      }
    }
    return playableCards.get(0);
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }

  private int nbTrumpInNature(DiscardDeck playedCards, CardColor trump){
    return 9 - playedCards.getCounterTrump() - hand.getNumberOfCardsByColor(hand.getContent(), trump);
  }
}
