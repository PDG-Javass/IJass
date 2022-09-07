package ch.ijass.engine;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;

import java.util.ArrayList;
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

  State() {
    board = new BoardDeck();
    idGame = counterIdGame++;
  }

  // todo : ArrayList the playable card en liste d'index de par rapport a hand

}
