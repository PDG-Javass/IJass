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
    //if(Objects.equals(getName(), "Chacha "))
      System.out.println( getName()+"'s hand : " + hand);
    // todo remember to remove this line
    Vector<Card> playableCards = hand.getPlayableCard(board, trump);

    // on a une seule carte jouable
    if (playableCards.size() == 1) {
      return playableCards.get(0);
    } else {
      int nbTrumpsHand = hand.getNumberOfCardsByColor(hand.getContent(), trump);

      // On est le premier à jouer
      if (board.numberOfCards() == 0) {

        // Cas 1 : On tire atout avec le meilleur atout
        int nbTrumpsNature = nbTrumpInNature(playedCards, trump);
        if (nbTrumpsHand > 2 && nbTrumpsNature > 0
            || (nbTrumpsHand == 2 && nbTrumpsNature > 0 && nbTrumpsNature < 4)) {
          Card bock = hand.getBockByColor(playableCards, playedCards, trump, true);
          if (bock != null) {
            return bock;
          } else {
            return hand.getLowestByColor(playableCards, trump, true);
          }
        }

        // Cas 2 : On joue un bock //todo : findBock au lieu de findAce
        Card bock = hand.findBock(playableCards, trump, playedCards);
        if (bock != null) {
          return bock;
        }

        // Cas 3 : On joue une petite carte parmis la couleur la plus presente
        return hand.getLowestByColor(playableCards, hand.getColorMostPresent(), false);
      }

      // On est pas le premier à jouer
      else {

        // Cas 1 : On peut prendre l'avantage sans couper
        Card winCard = hand.getAdvantageWithoutCut(board, trump);
        if (winCard != null) {
          return winCard;
        }

        // Cas 2 : On coupe car il y a beaucoup de points à gagner
        if ((board.countPoints(trump) > 10)
            && hand.getNumberOfCardsByColor(playableCards, trump) > 0) {
          return hand.getLowestByColor(playableCards, trump, true);
        }

        // Cas 3 : On joue une petite carte
        Card card = smallCard(playableCards, trump, 4);
        if (card != null) {
          return card;
        }

        // Cas 4 : On joue une carte moyenne
        card = smallCard(playableCards, trump, 8);
        if (card != null) {
          return card;
        }

        // Cas 5 : On joue une carte au hasard
        return playableCards.get((int) (Math.random() * playableCards.size()));
      }
    }
  }

  @Override
  public CardColor chooseTrump() {
    return hand.getColorMostPresent();
  }

  private int nbTrumpInNature(DiscardDeck playedCards, CardColor trump) {
    return 9
        - playedCards.getCounterTrump()
        - hand.getNumberOfCardsByColor(hand.getContent(), trump);
  }

  private Card smallCard(Vector<Card> cards, CardColor trump, int limit) {
    for (Card card : cards) {
      if (card.getColor() != trump && card.getValue().ordinal() < limit) {
        return card;
      }
    }
    return null;
  }

}
