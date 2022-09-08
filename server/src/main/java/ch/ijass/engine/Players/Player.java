package ch.ijass.engine.Players;

import ch.ijass.engine.Cards.*;
import java.util.Collection;

/**
 * Un joueur.
 */
public abstract class Player {
  /**
   * L'identifiant du joueur.
   */
  private final int id;

  /**
   * Le nom du joueur.
   */
  private final String name;

  /**
   * La main du joueur.
   */
  protected HandDeck hand;

  /**
   * L'équipe du joueur.
   */
  private Team team;

  /**
   * Le compteur statique de joueurs
   */
  private static int counterId = 0;

  /**
   * Crée un joueur en spécifiant son nom et son équipe
   * @param name le nom du joueur
   * @param team l'équipe du joueur
   */
  public Player(String name, Team team) {
    this.id = (counterId++) % 4;
    this.name = name;
    this.team = team;
    hand = new HandDeck();
  }

  /**
   * @return la main du joueur
   */
  public HandDeck getHand() {
    return hand;
  }

  /**
   * @return le nom du joueur
   */
  public String getName() {
    return name;
  }

  /**
   * @return l'identifiant du joueur
   */
  public int getId() {
    return id;
  }

  /**
   * @return l'équipe du joueur
   */
  public Team getTeam() {
    return team;
  }

  /**
   * Spécifie si le joueur est un bot ou non (s'il choisit sa carte tout seul ou utilise l'input utilisateur)
   * @return true si le joueur est un bot, false sinon
   */
  public boolean isBot() {
    return true;
  }

  /**
   * Ordonne la main du joueur par couleur puis valeur
   */
  public void sortHand() { hand.sort(); }

  /**
   * Joue une carte de la main du joueur
   * @param playMat le plateau de jeu
   * @param playedCards les cartes déjà jouées
   * @param trump la couleur atout
   * @param choice la carte choisie par le joueur (s'il n'est pas un bot)
   * @return la carte jouée
   */
  public abstract Card play(BoardDeck playMat, DiscardDeck playedCards, CardColor trump, int choice);

  /**
   * Fait choisir l'atout au joueur
   * @implNote Cette méthode est appelée uniquement si le joueur est un bot
   * @return la couleur atout choisie
   */
  public abstract CardColor chooseTrump();

  /**
   *
   * Ajoute une carte à la main du joueur
   * @param card la carte à ajouter
   */
  public void addCard(Card card) {
    hand.addCard(card);
    card.setPlayerId(this);
  }

  /**
   * Vide la main du joueur
   */
  public void emptyHand() {
    hand.emptyDeck();
  }
}
