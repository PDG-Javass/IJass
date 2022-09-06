package ch.ijass.engine;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;

import java.util.Collection;
import java.util.Vector;
import lombok.Data;

@Data
public class State {
  static int counterIdGame = 0;
  public int idGame;

  public int counterRound;
  public int trump;

  public int counterFold;
  public int idFirstForFold;
  public BoardDeck board;
  public int idWinner;

  public int scorePerson;
  public int scoreBot;
  public Vector<Card> hand;
  public Vector<Integer> playableCards;
  public Vector<Card> playedCards;


  public void setHand(Vector<Card> hand) {
    this.hand = new Vector<>();
    this.hand.addAll(hand);
  }

  public void setPlayableCards(Vector<Integer> playableCards) {
    this.playableCards = new Vector<>();
    this.playableCards.addAll(playableCards);
  }
  public void clearPlayedCards() { playedCards.clear(); }

  public void addPlayedCard(Card card) { playedCards.add(card); }

  public void addPlayedCards(Collection<Card> cards) {
    playedCards.addAll(cards);
  }



  State() {
    trump = -1; // Atout pas encore choisi
    board = new BoardDeck();
    playedCards = new Vector<>();
    hand = new Vector<>();
    playableCards = new Vector<>();
    idGame = counterIdGame++;
  }

  // todo : vector the playable card en liste d'index de par rapport a hand

}
