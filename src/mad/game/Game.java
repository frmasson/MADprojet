/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.game;

import java.util.ArrayList;
import java.util.TreeSet;
import mad.cards.Card;

/**
 *
 * @author Christophe
 */
public class Game {
    private ArrayList players;
    private TreeSet cards;
    private TreeSet discardedCards;
    
    
    public void addPlayer(Player player){
        players.add(player);
    }
    
    public void removePlayer(Player player){
        players.remove(player);
    }
    
    public void killPlayer(Player player){
    //@TODO
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList Players) {
        this.players = Players;
    }

    public TreeSet getCards() {
        return cards;
    }

    public void setCards(TreeSet cards) {
        this.cards = cards;
    }

    public TreeSet getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(TreeSet discardedCards) {
        this.discardedCards = discardedCards;
    }
    
    public void addDiscardedCard(Card card) {
        this.discardedCards.add(card);
    }
}
