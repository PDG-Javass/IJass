package ch.ijass.engine;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import ch.ijass.engine.Cards.DiscardDeck;
import java.util.ArrayList;
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
  public ArrayList<Card> hand;
  public ArrayList<Integer> playableCards;
  public DiscardDeck playedCards;

  public void setHand(ArrayList<Card> hand) {
    this.hand = new ArrayList<>();
    this.hand.addAll(hand);
  }

  public void setPlayableCards(ArrayList<Integer> playableCards) {
    this.playableCards = new ArrayList<>();
    this.playableCards.addAll(playableCards);
  }

  State() {
    trump = -1; // Atout pas encore choisi
    board = new BoardDeck();
    playedCards = new DiscardDeck();
    hand = new ArrayList<>();
    playableCards = new ArrayList<>();
    idGame = counterIdGame++;
  }

  // todo : ArrayList the playable card en liste d'index de par rapport a hand

}
