package mad.game;

import java.util.ArrayList;
import mad.cards.Card;
import mad.cards.CardSet;

/**
 *
 * @author Christophe
 */
public class Game {

    private ArrayList<Player> players;
    private CardSet cards;
    private CardSet discardedCards;
    private int startingHitPoints;
    private int nbTours = 0;
    private int nbPlayers = 0;
    int nbPlayerAlive = 0;

    public Game(int nbPlayers, int startingHitPoints, CardSet cards) {
        this.nbPlayers = nbPlayers;
        this.nbPlayerAlive = nbPlayers;
        this.cards = cards;
        this.cards.shuffle();
        this.startingHitPoints = startingHitPoints;
        createPlayers();
    }

    private void createPlayers() {
        Player newPlayer = new HumanPlayer(startingHitPoints);
        createStartingPlayerCardSet(newPlayer);
        addPlayer(newPlayer);
        for (int i = 0; i <= this.nbPlayers; i++) {
            newPlayer = new AIPlayer(startingHitPoints);
            createStartingPlayerCardSet(newPlayer);
            addPlayer(newPlayer);
        }
    }

    private void createStartingPlayerCardSet(Player player) {
        boolean isntFull = true;
        while (isntFull) {
            isntFull = player.addCard(cards.pop());
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void killPlayer(Player player) {
        this.nbPlayerAlive--;
        //@TODO
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList Players) {
        this.players = Players;
    }

    public CardSet getCards() {
        return cards;
    }

    public void setCards(CardSet cards) {
        this.cards = cards;
    }

    public CardSet getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(CardSet discardedCards) {
        this.discardedCards = discardedCards;
    }

    public void addDiscardedCard(Card card) {
        this.discardedCards.add(card);
    }

    public int getNbTours() {
        return nbTours;
    }

    public void incrementNbTours() {
        this.nbTours++;
    }

    public void playRound(){
        while(!isEnded()){
        for (Player player : players){
            playPlayerRound(player);
        }   
        incrementNbTours();
        
        }        
    }
    
    public boolean isEnded(){
    return (nbPlayers - nbPlayerAlive) > 1;
    }

