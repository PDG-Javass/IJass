package ch.ijass.engine;

import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Cards.Card;
import java.util.Vector;
import lombok.Data;

@Data
public class State {
  static int counterIdGame = 0;
  public int getGameId() {
    return idGame;
  }

  public int idGame;

  public int counterRound;
  public int trump;

  public int counterFold;
  public int idFirstForFold;

  public BoardDeck getBoard() {
    return board;
  }

  public BoardDeck board;
  public int idWinner;

  public int scorePerson;
  public int scoreBot;
  public Vector<Card> hand;
  public Vector<Card> playableCards;
  public Vector<Card> playedCards;

  public void clearPlayedCards() { playedCards.clear(); }

  public void addPlayedCard(Card card) { playedCards.add(card); }

  State() {
    board = new BoardDeck();
    playedCards = new Vector<>();
    hand = new Vector<>();
    playableCards = new Vector<>();
    idGame = counterIdGame++;
  }

  // todo : vector the playable card en liste d'index de par rapport a hand

}
