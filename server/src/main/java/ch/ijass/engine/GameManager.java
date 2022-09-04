package ch.ijass.engine;

import ch.ijass.engine.Cards.*;
import ch.ijass.engine.Cards.BoardDeck;
import ch.ijass.engine.Players.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class GameManager {
  private Player firstForRound;
  private Player firstForFold;
  private Vector<Player> players;

  private HashMap<Player, Card> ownership;
  private Team team1, team2;
  int counterRound;
  int counterFold;

  public void setTrump(CardColor trump) {
    CardColor.setTrump(trump);
  }

  BoardDeck playMat;
  Deck playedCards;
  StartingDeck initialDeck;

  final int CINQDEDER = 5;

  final int POINTS = 1000;

  /*
  GameManager() {
      this(null);
      players = new Vector<Player>();
      players.add(new BotPlayer("Lapinou ", team1));
      players.add(new BotPlayer("Chacha ", team2));
      players.add(new BotPlayer("Titi ", team1));
      players.add(new PersonPlayer("Toto ", team2));

  }

  GameManager(Vector<Player> players) {
      this.players = new Vector<>(players);
      playMat = new BoardDeck();
      firstForFold = players.firstElement();
      firstForRound = players.firstElement();
      counterRound = 1;
  }
   */

  GameManager() {
    team1 = new Team();
    team2 = new Team();
    players = new Vector<Player>();
    ownership = new HashMap<>();
    players.add(new PersonPlayer("Toto ", team1));
    players.add(new BotPlayer("Titi ", team2));
    players.add(new BotPlayer("Lapinou ", team1));
    players.add(new BotPlayer("Chacha ", team2));

    playMat = new BoardDeck();
    firstForFold = players.firstElement();
    firstForRound = players.firstElement();
    counterRound = 1;
  }

  public void setPlayers(Vector<Player> players) {
    this.players = players;
  }

  Vector<Player> getPlayers() {
    return players;
  }

  public void initiateRound() {
    playMat = new BoardDeck();
    playedCards = new Deck();
    initialDeck = new StartingDeck();
    counterFold = 1;
    distribute();
  }

  public void distribute() {

    // Vérifie que l'état des joueurs est ok pour la distribution
    if (initialDeck.numberOfCards() != 36)
      throw new RuntimeException("Can not distribute cards if the initial deck is incomplete");
    for (Player player : players) {
      if (player.getHand().numberOfCards() != 0)
        throw new RuntimeException("Can not distribute if one of the players still have cards");
    }
    // Distribution des cartes
    while (initialDeck.numberOfCards() > 0) {
      for (Player player : players) {
        player.addCard(initialDeck.pickCardRandomly());
      }
    }
  }

  public void doOneRound() {
    initiateRound();
    updateFirstForRound(); // todo update le systeme de nexte player
    firstForFold = firstForRound;
    CardColor.setTrump(firstForRound.chooseTrump());

    System.out.println("\n\nRound " + counterRound);
    System.out.println("Trump is " + CardColor.getTrump());

    // Déroulement de la manche
    while (counterFold < 10 && getHighestScore() < POINTS) {
      doOneFold();
    }

    // Vide les mains des joueurs TODO: retirer si le joueur retire la ref dans play
    for (Player player : players) {
      player.emptyHand();
    }
    counterRound++;
    // Vide les cartes jouées pendant le round
    playedCards.clear();
  }

  public Player find7ofDiamonds() {
    for (Player player : players) {
      if (player.getHand().contains(new Card(CardColor.DIAMONDS, CardValue.SEVEN)))
        return player;
    }
    return null;
  }

  public int getHighestScore() {
    return Math.max(team1.getScore(), team2.getScore());
  }

  public void updateFirstForRound() {
    if (counterRound == 1) {
      firstForRound = find7ofDiamonds();
    } else {
      firstForRound = players.get((players.indexOf(firstForRound) + 1) % 4);
    }
  }

  private void playersPlay() {
    ownership.clear();
    int startIndex = players.indexOf(firstForFold);
    for (int i = 0; i < 4; ++i) {
      Player current = players.get((startIndex + i) % 4);
      Card play = current.playCard(playMat, CardColor.getTrump());
      playMat.addCard(play);
      ownership.put(current, play);
    }
  }

  public void doOneFold() {
    System.out.println("Fold " + counterFold);
    playersPlay();
    Card winning = playMat.getHighestCard();
    counterFold++;
    for(Map.Entry<Player, Card> os : ownership.entrySet()) {
      if (os.getValue() == winning)
        firstForFold = os.getKey();
    }
    firstForFold.getTeam().addPoints(playMat.countPoints(CardColor.getTrump()));

    playedCards.addCards(playMat.getContent());
    playMat.clear();

    if (counterFold == 9) firstForFold.getTeam().addPoints(CINQDEDER);
    System.out.println("the winner is :" + firstForFold.getName());
    System.out.println("the bot score is :" + players.get(1).getTeam().getScore());
    System.out.println("the person score is :" + players.get(0).getTeam().getScore());
  }

  void playing() {
    while (getHighestScore() < POINTS) { // todo ajouter vérification de victoire à chaque plie
      doOneRound();
    }
  }

  public static void main(String[] args) {
    GameManager game = new GameManager();
    game.playing();
    System.out.println("Hello");
  }
}
