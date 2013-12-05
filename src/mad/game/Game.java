package mad.game;

import java.util.ArrayList;
import mad.cards.Card;
import mad.cards.CardSet;

/**
 *
 * @author Christophe
 */
public class Game {

    private ArrayList players;
    private CardSet cards;
    private CardSet discardedCards;
    private int startingHitPoints;
    private int nbTours = 0;

    public Game(int nbPlayers, int startingHitPoints, CardSet cards) {
        this.cards = cards;
        this.cards.shuffle();
        this.startingHitPoints = startingHitPoints;
        createPlayers(nbPlayers);
    }

    private void createPlayers(int nbPlayers) {
        Player newPlayer = new HumanPlayer(startingHitPoints);
        createStartingPlayerCardSet(newPlayer);
        addPlayer(newPlayer);
        for (int i = 0; i <= nbPlayers; i++) {
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
        ++this.nbTours;
    }

    public void playRound(){
    
    }
}

