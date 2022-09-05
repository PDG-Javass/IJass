package ch.ijass.engine;

import ch.ijass.engine.Cards.*;
import ch.ijass.engine.Players.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Vector;

public class GameManager {
  private Player firstForRound;
  private Player firstForFold;

  private Player current;
  private Vector<Player> players;
  private CardColor trump;
  // private int counterRound;
  // private int counterFold;
  // private BoardDeck playMat;

  private State state;
  private StartingDeck initialDeck;
  private final int CINQDEDER = 5;
  private final int POINTS = 1000;

  public GameManager() {
    state = new State();
    Team team1 = new Team();
    Team team2 = new Team();
    players = new Vector<Player>();
    players.add(new PersonPlayer("Toto ", team1));
    players.add(new BotPlayer("Titi ", team2));
    players.add(new BotPlayer("Lapinou ", team1));
    players.add(new BotPlayer("Chacha ", team2));

    firstForFold = players.firstElement();
    state.setIdFirstForFold(firstForFold.getId());
    firstForRound = players.firstElement();
    state.setCounterRound(1);
  }

  Vector<Player> getPlayers() {
    return players;
  }

  public int getGameId() { return state.getGameId(); }

  public void setTrump(int trump) {
    if (trump < 0 || trump >= CardColor.values().length)
      throw new RuntimeException("Ordinal out of bounds for CardColor");
    this.trump = CardColor.values()[trump];
    state.setTrump(trump);
  }

  public void initiateRound() {
    initialDeck = new StartingDeck();
    state.setCounterFold(1);
    current = firstForRound;
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
    setHand();
  }

  public void doOneRound() {
    initiateRound();
    updateFirstForRound(); // todo update le systeme de nexte player
    firstForFold = firstForRound;
    state.setIdFirstForFold(firstForFold.getId());
    trump = firstForRound.chooseTrump();
    state.setTrump(trump.ordinal());

    System.out.println("\n\nRound " + state.getCounterRound());
    System.out.println("Trump is " + trump);

    // Déroulement de la manche
    while (state.getCounterFold() < 10 && getHighestScore() < POINTS) {
      doOneFold();
    }

    // Vide les mains des joueurs TODO: retirer si le joueur retire la ref dans play
    for (Player player : players) {
      player.emptyHand();
    }
    state.setCounterRound(state.getCounterRound() + 1);
    // Vide les cartes jouées pendant le round
    state.clearPlayedCards();
  }

  public Player getPlayerById(int id) {
    for (Player player : players) {
      if (player.getId() == id) {
        return player;
      }
    }
    return null;
  }

  public Player find7ofDiamonds() {
    for (Player player : players) {
      Card card = player.getHand().findCard(CardColor.DIAMONDS, CardValue.SEVEN);
      if (card != null) return getPlayerById(card.getPlayerId());
    }
    return null;
  }

  public int getHighestScore() {
    return Math.max(
        players.firstElement().getTeam().getScore(), players.lastElement().getTeam().getScore());
  }

  public void updateFirstForRound() {
    if (state.getCounterRound() == 1) {
      firstForRound = find7ofDiamonds();
    } else {
      firstForRound = players.get((players.indexOf(firstForRound) + 1) % 4);
    }
  }

  private CardColor everybodyPlays() { // 🎵🎵🎵
    Player current = firstForFold;
    Card firstCard = current.playCard(state.getBoard(), trump);
    state.getBoard().addCard(firstCard);
    CardColor colorAsked = firstCard.getColor();
    state.setPlayableCards(players.get(0).getHand().getPlayableCard(state.getBoard(), trump));
    setHand();

    int startIndex = players.indexOf(current) + 1;
    for (int i = 0; i < 3; ++i) {

      state.getBoard().addCard(players.get((startIndex + i) % 4).playCard(state.getBoard(), trump));
      state.setPlayableCards(players.get(0).getHand().getPlayableCard(state.getBoard(), trump));
      setHand();
      try {
        System.out.println(
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(state));
      } catch (Exception e) {
      }
      ;
    }
    return colorAsked;
  }

  public void playUntilNextPersonPlayer() {
    Player curr = current;
    Card choice;
    do {
      choice = curr.chooseCard(state.board, trump);
      state.board.addCard(choice);
      state.addPlayedCard(choice);
      nextPlayer();
    } while(choice != null && state.board.size() < 4);
    if (state.board.size() == 4) {
      state.clearPlayedCards();
      firstForFold = players.get(state.board.getFoldWinner(state.board.colorAsked(), trump));
      firstForFold.getTeam().addPoints(state.board.countPoints(trump));
      state.setIdFirstForFold(firstForFold.getId());

      // On set les variables de l'état
      state.setIdWinner(firstForFold.getId());
      state.setScoreBot(players.get(1).getTeam().getScore());
      state.setScorePerson(players.get(0).getTeam().getScore());

      // On déplace les cartes jouées du board vers le discardDeck
      state.getBoard().emptyDeck();
      ++state.counterFold;
      if (endGame())
        return;

      if (state.getCounterFold() == 9) {
        firstForFold.getTeam().addPoints(CINQDEDER);
        state.counterFold = 1;
        state.counterRound++;
      }
    }
  }

  public void nextPlayer() {
    current = players.get((players.indexOf(current) + 1) % 4);
  }

  public State compute(int playerId, int cardChoice) {
    if (playerId != current.getId())
      throw new RuntimeException("Invalid player id");
    current.playChoice(cardChoice);
    nextPlayer();
    playUntilNextPersonPlayer();
    state.playedCards.clear();
    state.playedCards.addAll(current.getHand().getContent());
    state.playableCards.clear();
    state.playableCards.addAll(current.getHand().getPlayableCard(state.getBoard(), trump));
    return state;
  }

  public void doOneFold() {
    // On commence le tour
    System.out.println("Fold " + state.getCounterFold());
    state.setPlayableCards(players.get(0).getHand().getPlayableCard(state.getBoard(), trump));
    CardColor colorAsked = everybodyPlays();
    state.setCounterFold(state.getCounterFold() + 1);

    // On calcul qui gagne la plie et on attribut les points
    firstForFold = getPlayerById(state.getBoard().getFoldWinner(colorAsked, trump));
    state.setIdFirstForFold(firstForFold.getId());
    firstForFold.getTeam().addPoints(state.getBoard().countPoints(trump));

    // On set les variables de l'état
    state.setIdWinner(firstForFold.getId());
    state.setScoreBot(players.get(1).getTeam().getScore());
    state.setScorePerson(players.get(0).getTeam().getScore());

    // On deplace les cartes jouées du board vers le discardDeck
    state.getBoard().emptyDeck();

    if (state.getCounterFold() == 9) firstForFold.getTeam().addPoints(CINQDEDER);
    System.out.println("the winner is :" + firstForFold.getName());
    System.out.println("the bot score is :" + players.get(1).getTeam().getScore());
    System.out.println("the person score is :" + players.get(0).getTeam().getScore());
  }

  public void playing() {
    while (getHighestScore() < POINTS) { // todo ajouter vérification de victoire à chaque plie
      doOneRound();
    }
  }

  public void setHand() {
    state.setHand(players.firstElement().getHand().getContent());
  }

  public boolean endGame() {
    return getHighestScore() > POINTS;
  }

  public static void main(String[] args) {
    GameManager game = new GameManager();
    game.playing();
    System.out.println("Helllo");
  }
}
