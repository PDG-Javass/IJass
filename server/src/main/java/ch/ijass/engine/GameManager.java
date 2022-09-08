package ch.ijass.engine;

import ch.ijass.engine.Cards.*;
import ch.ijass.engine.Players.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
// TODO : Méthode coup par coup
public class GameManager extends State {
  private Player firstForRound;
  private Player firstForFold;
  private Player current;
  private ArrayList<Player> players;
  private CardColor trump;
  private boolean inProgress;

  public State getState() {
    return state;
  }

  private State state;
  // private DiscardDeck playedCards;
  private StartingDeck initialDeck;
  private final int CINQDEDER = 5;

  public GameManager() {
    state = new State();
    Team team1 = new Team();
    Team team2 = new Team();
    players = new ArrayList<Player>();
    players.add(new PersonPlayer("Toto ", team1));
    players.add(new BotPlayer("Titi ", team2));
    players.add(new BotPlayer("Lapinou ", team1));
    players.add(new BotPlayer("Chacha ", team2));

    firstForFold = players.get(0);
    state.setIdFirstForFold(firstForFold.getId());
    firstForRound = players.get(0);
    state.setCounterRound(1);
    state.setCounterFold(1);
    initiateRound(players.get(0).getId());
  }

  public int getGameId() { return  state.getIdGame(); }

  public void setTrump(int trump) {
    this.trump = CardColor.values()[trump];
    state.setTrump(trump);
  }

  public boolean nextTurn() {
    if (current.isBot()) {
      state.board.addCard(current.play(state.board, state.getPlayedCards(), trump, 0)); // Le choix n'est pas utilisé dans
                                                                                    // la redef de play dans BotPlayer
      nextPlayer();
      return true;
    }
    return false;
  }

  public void updateStateForEndFold(int playerId) {
    // Assignation du gagnant de la plie
    int foldWinnerId = state.board.getFoldWinner(trump);
    state.setIdWinner(foldWinnerId);
    Player winner = getPlayerById(foldWinnerId);
    winner.getTeam().addPoints(state.board.countPoints(trump));
    firstForFold = winner;
    state.idFirstForFold = foldWinnerId;
    state.counterFold++;
    current = firstForFold;

    if (state.counterFold == 10) {
      winner.getTeam().addPoints(CINQDEDER);
      state.counterRound++;
      state.counterFold = 1;
      trump = null;
      state.setTrump(-1);
    }
    // On flush les carte du tapis dans les cartes jouées durant la plie
    state.getPlayedCards().addFold(state.board.getContent(), trump);
    state.board.emptyDeck();

    // On assigne la nouvelle main du joueur à l'état
    Player person = getPlayerById(playerId);
    state.setHand(person.getHand().getContent());
    state.setPlayableCards(getIndexArrayList(person.getHand().getContent(),
            person.getHand().getPlayableCard(state.board, trump)));

    // Assignation des scores des équipes
    Team player = person.getTeam(), bot = players.get((players.indexOf(person) + 1) % 4).getTeam();
    state.setScoreBot(bot.getScore());
    state.setScorePerson(player.getScore());
  }

  public void updateStateWhileFold(int playerId) {
    Player person = getPlayerById(playerId);
    state.idFirstForFold = firstForFold.getId();
    state.setHand(person.getHand().getContent());
    state.setPlayableCards(getIndexArrayList(person.getHand().getContent(),
            person.getHand().getPlayableCard(state.board, trump)));
    if (trump != null)
      state.setTrump(trump.ordinal());
    else
      state.setTrump(-1);
  }

  public ArrayList<Integer> getIndexArrayList(ArrayList<Card> cards, ArrayList<Card> playable) {
    ArrayList<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < cards.size(); i++) {
      if (playable.contains(cards.get(i)))
        indexes.add(i);
    }
    return indexes;
  }

  public void playUntilPlayerTurn(int id) {
    while (nextTurn());
  }

  public void playUntilEverybodyPlayed() {
    while (state.board.numberOfCards() < 4)
      nextTurn();
  }

  public void playerTurn(int cardChoice) {
    state.board.addCard(current.play(state.board, state.getPlayedCards(), trump, cardChoice));
    nextPlayer();
  }

  public State startFold(int playerId) {
    playUntilPlayerTurn(playerId);
    updateStateWhileFold(playerId);
    return state;
  }
  public State endFold(int playerId, int cardChoice) {
    playerTurn(cardChoice);
    playUntilEverybodyPlayed();
    updateStateForEndFold(playerId);
    return state;
  }

  public void initiateRound(int playerId) {
    initialDeck = new StartingDeck();
    clearHands();
    state.playedCards.emptyDeck();
    distribute();
    getPlayerById(playerId).sortHand();
    updateFirstForRound();
    if (firstForRound.isBot()) {
      setTrump(firstForRound.chooseTrump().ordinal());
    }
    firstForFold = firstForRound;
    current = firstForFold;
    updateStateWhileFold(playerId);
  }

  public void clearHands() {
    for (Player player : players)
      player.emptyHand();
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

  public void updateFirstForRound() {
    if (state.getCounterRound() == 1) {
      firstForRound = find7ofDiamonds();
    } else {
      firstForRound = players.get((players.indexOf(firstForRound) + 1) % 4);
    }
  }
  public void nextPlayer() {
    current = players.get((players.indexOf(current) + 1) % 4);
  }

  public void setHand() {
    if (current != null)
      state.setHand(current.getHand().getContent());
  }

  // fonction qui permet de trouver a quel indice se trouve les cartes jouables au sain de la hand
  private void setPlayable(){
    ArrayList<Integer> indexPlayable = new ArrayList<>();
    int index = 0;
    for (Card card : state.getHand()) {
      if (players.get(0).getHand().getPlayableCard(state.getBoard(), trump).contains(card)) {
        indexPlayable.add(index);
      }
      index++;
    }
    state.setPlayableCards(indexPlayable);
  }
}
