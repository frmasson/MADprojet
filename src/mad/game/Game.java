package mad.game;

import java.util.ArrayList;
import mad.cards.Card;
import mad.cards.CardSet;
import mad.views.VuePartie;

/**
 *
 * @author Christophe
 */
public class Game {

    private VuePartie vue;
    private ArrayList<Player> players;
    private PlayerRound playerRound;
    private int currentPlayer;
    private CardSet cards;
    private CardSet discardedCards;
    private int startingHitPoints;
    private int nbTours = 0;
    private int nbPlayers = 0;
    int nbPlayerAlive = 0;

    public Game(VuePartie vue, int nbPlayers, int startingHitPoints, CardSet cards) {
        this.vue = vue;
        this.nbPlayers = nbPlayers;
        this.nbPlayerAlive = nbPlayers;
        this.cards = cards;
        this.cards.shuffle();
        this.startingHitPoints = startingHitPoints;
        this.currentPlayer = 0;
        createPlayers();
        /*
         actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "A string that may specify a command (possibly one of several) associated with the event.");
         */
        playerRound = new PlayerRound(this);
        playRound();
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

    public void nextPlayerRound() {
        if (!isEnded()) {
            currentPlayer = (currentPlayer + 1) % nbPlayers;
            playRound();
        }
    }

    public void playRound() {
        //grosso modo, déclanché playerround... qui fait??? 
        
        //playerRound.actionPerformed(null);
       // playerRound = new PlayerRound(this, players.get(currentPlayer));
        if (currentPlayer == 0){
            unlockPlayerCards();
        }      
        
    }
    
    public void playPlayerCard(int pos){
        players.get(0).playCard(pos);
    }
    
    public void playPlayerDefenseCard(int pos){
        players.get(0).playCard(pos);
    }

    public void playNPCCard(Player player){
        
    }
    
    public void playNPCDefenseCard(Player player){
    
    }
       
    
    public boolean isEnded() {
        return (nbPlayers - nbPlayerAlive) > 1;
    }
    
   private void unlockPlayerCards(){
       boolean playableCards[] = determinePlayableCards();
        vue.unlockCards(playableCards);
    }
    
    private boolean[] determinePlayableCards(){
        return null;        
    }
}
