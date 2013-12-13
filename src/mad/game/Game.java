package mad.game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mad.cards.AttackCard;
import mad.cards.Card;
import mad.cards.CardSet;
import mad.cards.ConstructionCard;
import mad.cards.DefenceCard;
import mad.views.VuePartie;

/**
 *
 * @author Christophe
 */
public class Game {

    private VuePartie vue;
    private ArrayList<Player> players;
    private int currentPlayer;
    private CardSet cards;
    private CardSet discardedCards;
    private Card currentlyPlayedCard;
    private Card previousPlayedCard;
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
        this.players = new ArrayList<>();
        this.discardedCards = new CardSet();
        createPlayers();

        playRound();
    }

    private void createPlayers() {
        Player newPlayer = new HumanPlayer(startingHitPoints);
        createStartingPlayerCardSet(newPlayer);
        addPlayer(newPlayer);
        for (int i = 0; i < this.nbPlayers - 1; i++) {
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

    public void nextRound() {
        if (!isEnded()) {
            currentPlayer = (currentPlayer + 1) % nbPlayers;
            playRound();
        }
    }

    public void playRound() {
        System.out.println("on lance playRound est le currentPlayer est = " + currentPlayer);
        //grosso modo, déclanché playerround... qui fait??? 

        //playerRound.actionPerformed(null);
        // playerRound = new PlayerRound(this, players.get(currentPlayer));
        if (currentPlayer == 0) {
            unlockPlayerCards();
        } else {
            npcRound();
        }

    }

    private void npcRound() {
        npcSelectAttackCard();
        nextRound();

    }

    private void npcSelectAttackCard() {
        for (Card card : players.get(currentPlayer).getCards()) {
            if (card.getType().equals("Attack")) {
                AttackCard attackCard = (AttackCard)card;
                playCurrentNPCAttackCard(attackCard);
                break;
            }
        }
    }

    public void playerPlayCard(int pos) {
        System.out.println("le joueur a cliqué sur la " + pos + "e carte");
        previousPlayedCard = currentlyPlayedCard;
        currentlyPlayedCard = players.get(0).playCard(pos);
        //les cartes ne sont plus unlockées
        //vue.???
        if (currentlyPlayedCard.getType().equals("Attack")) {
            System.out.println("la carte " + currentlyPlayedCard.getName() + " est une carte d'attaque");
            AttackCard currentlyPlayedAttackCard = (AttackCard) currentlyPlayedCard;
            if (currentlyPlayedAttackCard.isAreaOfEffect()) {
                System.out.println("c'est un aoe donc pas de selection de target");
                applyEffects(null, currentlyPlayedAttackCard);
                //vue.???
            } else {
                // la vu doit selectionner une cible
                //vue.???
                System.out.println("Ce n'est pas un aoe donc le joueur doit selectionner une cible");
                vue.unlockTargetingPhase(getAliveOpponents());
            }
        } else if (currentlyPlayedCard.getClass() == DefenceCard.class) {
            // la cible est probablement le current player
            playerPlayDefenseCard(pos);
        } else if (currentlyPlayedCard.getClass() == ConstructionCard.class) {
            applyEffects(null, (ConstructionCard) currentlyPlayedCard);
        }
    }

    public void playerSelectTarget(int pos) {
        Player target = players.get(pos);
        applyEffects(target, (AttackCard) currentlyPlayedCard);
    }

    public void playerPlayDefenseCard(int pos) {
        currentlyPlayedCard = players.get(0).playCard(pos);
        players.get(0).playCard(pos);
    }

    public void playCurrentNPCAttackCard(AttackCard card) {
        // pour le moment, on choisis une cible au hazard
        Player target = null;
        if (!card.isAreaOfEffect()) {            
            target = npcSelectRandomTarget();            
        }
        applyEffects(target, card);
    }

    public Player npcSelectRandomTarget() {
        int posTarget = currentPlayer;
        while (posTarget == currentPlayer || players.get(posTarget).isKilled()) {
            posTarget = randomize(0, nbPlayers - 1);
        }
        return players.get(posTarget);
    }

    public void playNPCDefenseCard(Player player) {
    }

    public boolean isEnded() {
        return (nbPlayers - nbPlayerAlive) > 1 && !players.get(0).isKilled();
    }

    public boolean playerWon() {
        return isEnded() && !players.get(0).isKilled();
    }

    private void unlockPlayerCards() {
        boolean playableCards[] = determinePlayableCards();
        vue.unlockAttackPhase(playableCards);
    }

    private boolean[] determinePlayableCards() {
        boolean playableCards[] = new boolean[Player.NBMAXCARDS];
        ArrayList <Card> cards = players.get(0).getCards();
        for (int i = 0; i < Player.NBMAXCARDS; i++){
            playableCards[i] = cards.get(i).getType().equals("Attack");
        }
        return playableCards;
    }

    private void applyEffects(Player target, AttackCard card) {
        System.out.println("on applique les effets de la carte " + card.getName() + "sur le joueur " + players.indexOf(target));
        int dmg = card.getDamage();
        int attackRoll = randomize(1, 20);
        int defenceRoll;
        int indexOfPlayer;


        if (card.isAreaOfEffect()) {
             System.out.println("la carte est un aoe");
            for (Player aoeTarget : players) {
                indexOfPlayer = players.indexOf(aoeTarget);
                if (indexOfPlayer != currentPlayer && !aoeTarget.isKilled()) {
                    if (card.isResistible()) {
                        int targetDefenceBonus = aoeTarget.getDefenceBonus();
                        defenceRoll = randomize(1 + targetDefenceBonus, 20 + targetDefenceBonus);
                        if (attackRoll >= defenceRoll) {                           
                            aoeTarget.substractHitPoints(dmg);
                        } else {
                             dmg = card.resist(dmg);
                             aoeTarget.substractHitPoints(dmg);
                        }
                    } else {                        
                        aoeTarget.substractHitPoints(dmg);
                    }
                }
                vue.updateInterface();                
                vue.showOpponentAttack(card, indexOfPlayer);
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("la carte est single target");
            if (card.isResistible()) {
                int targetDefenceBonus = target.getDefenceBonus();
                defenceRoll = randomize(1 + targetDefenceBonus, 20 + targetDefenceBonus);
                if (attackRoll >= defenceRoll) {                    
                    target.substractHitPoints(dmg);
                } else {
                     dmg = card.resist(dmg);
                     target.substractHitPoints(dmg);
                }
            } else {
                target.substractHitPoints(dmg);
            }
        }
        System.out.println("avant le update");
        vue.updateInterface();
        System.out.println("apres le update");
        //showOpponentAttack(Carte carte, int cible(0 à 3))
       // vue.showOpponentAttack(card, );
        System.out.println("avant le wait");
        try {
            wait(1000);
        } catch (InterruptedException ex) {
            System.out.println("l'exeption du wait");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("apres le wait");
    }

    private void applyEffects(Player target, DefenceCard card) {
    }

    private void applyEffects(Player target, ConstructionCard card) {
        if (card.getType().equals("Factory")) {
            players.get(currentPlayer).addFactory(new Factory(card));
        } else if (card.getType().equals("ResearchCenter")) {
            players.get(currentPlayer).setResearchCenter(new ResearchCenter(card));
        }
        vue.updateInterface();
    }

    private int randomize(int minValue, int maxValue) {
        return minValue + (int) Math.ceil(Math.random() * (maxValue - minValue));
    }

    private boolean[] getAliveOpponents() {
        boolean aliveOpponents[] = new boolean[nbPlayers - 1];
        for (int i = 0; i < players.size() - 1; i++) {
            aliveOpponents[i] = !players.get(i+1).isKilled();
        }        
        return aliveOpponents;
    }
}
