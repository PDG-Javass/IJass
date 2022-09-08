package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.CardColor;
import ch.ijass.engine.Cards.DiscardDeck;
import java.util.ArrayList;

public class BotPlayer extends Player {

  public BotPlayer(String name, Team team) {
    super(name, team);
  }

  @Override
  public Card play(BoardDeck board, DiscardDeck playedCards, CardColor trump, int choice) {

    ArrayList<Card> playableCards = hand.getPlayableCard(board, trump);
    Card choosenCard = null;

    System.out.println(getName() + "'s hand : " + hand);
    System.out.println( "discard deck : " + playedCards);

    // on a une seule carte jouable
    if (playableCards.size() == 1) {
      return hand.play(playableCards.get(0));

    } else {
      // On compte le nombre d'atout qu'on a en main
      int nbTrumpsHand = hand.getNumberOfCardsByColor(hand.getContent(), trump);

      // On est le premier à jouer
      if (board.numberOfCards() == 0) {

        // Cas 1 : On tire atout avec le meilleur atout
        int nbTrumpsNature = nbTrumpInNature(playedCards, trump);
        if ((nbTrumpsHand > 2 && nbTrumpsNature > 0 )
            || (nbTrumpsHand == 2 && nbTrumpsNature > 0 && nbTrumpsNature < 4)) {
          choosenCard = hand.getBockByColor(playableCards, playedCards, trump, true);
          if (choosenCard != null) {
            return hand.play(choosenCard);
          } else {
            return hand.play(hand.getLowestByColor(playableCards, trump, true));
          }
        }

        // Cas 2 : On joue un bock
        choosenCard = hand.findBock(playableCards, trump, playedCards);
        if (choosenCard != null) {
          return hand.play(choosenCard);
        }

        // Cas 3 : On joue une petite carte parmis la couleur la plus présente si ce n'est pas de l'atout
        choosenCard = hand.getLowestByColor(playableCards, hand.getColorMostPresent(), false);
        if(choosenCard.getColor() != trump) {
          return hand.play(choosenCard);
        }
      }

      // On est pas le premier à jouer

      // On regarde si c'est c'est l'équipe adverse qui tient la plie
      else if (board.getFoldWinner(trump) % 2 != getId() % 2) {

        // Cas 1 : On peut prendre l'avantage sans couper
        choosenCard = hand.getAdvantageWithoutCut(board, trump);
        if (choosenCard != null) {
          return hand.play(choosenCard);
        }

        // Cas 2 : On coupe car il y a beaucoup de points à gagner
        if ((board.countPoints(trump) > 10)
                && hand.getNumberOfCardsByColor(playableCards, trump) > 0) {
          return hand.play(hand.getLowestByColor(playableCards, trump, true));
        }
      }


      // On a rien de bien à jouer

      // Cas 1 : On joue une petite carte
      choosenCard = smallCard(playableCards, trump, 4);
      if (choosenCard != null) {
        return hand.play(choosenCard);
      }

      // Cas 2 : On joue une carte moyenne
      choosenCard = smallCard(playableCards, trump, 8);
      if (choosenCard != null) {
        return hand.play(choosenCard);
      }

      // Cas 3 : On joue une carte au hasard
      return hand.play(playableCards.get((int) (Math.random() * playableCards.size())));
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

  private Card smallCard(ArrayList<Card> cards, CardColor trump, int limit) {
    for (Card card : cards) {
      if (card.getColor() != trump && card.getValue().ordinal() < limit) {
        return card;
      }
    }
    return null;
  }
}
