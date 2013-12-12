package mad.game;

import java.util.ArrayList;
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
        createPlayers();

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

    public void nextRound() {
        if (!isEnded()) {
            currentPlayer = (currentPlayer + 1) % nbPlayers;
            playRound();
        }
    }

    public void playRound() {
        //grosso modo, déclanché playerround... qui fait??? 

        //playerRound.actionPerformed(null);
        // playerRound = new PlayerRound(this, players.get(currentPlayer));
        if (currentPlayer == 0) {
            unlockPlayerCards();
        }

    }

    public void playerPlayCard(int pos) {
        previousPlayedCard = currentlyPlayedCard;
        currentlyPlayedCard = players.get(0).playCard(pos);
        //les cartes ne sont plus unlockées
        //vue.???
        if (currentlyPlayedCard.getClass() == AttackCard.class) {
            AttackCard currentlyPlayedAttackCard = (AttackCard) currentlyPlayedCard;
            if (currentlyPlayedAttackCard.isAreaOfEffect()) {
                applyEffects(null, currentlyPlayedAttackCard);
                //vue.???
            } else {
                // la vu doit selectionner une cible
                //vue.???
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

    public void playNPCCard(Player player) {
    }

    public void playNPCDefenseCard(Player player) {
    }

    public boolean isEnded() {
        return (nbPlayers - nbPlayerAlive) > 1;
    }

    private void unlockPlayerCards() {
        boolean playableCards[] = determinePlayableCards();
        //vue.unlockCards(playableCards);
    }

    private boolean[] determinePlayableCards() {
        return null;
    }

    private void applyEffects(Player target, AttackCard card) {
        int dmg = card.getDamage();
        int attackRoll = randomize(1, 20);
        int defenceRoll;

        if (target == null) {
            if (card.isAreaOfEffect()) {
                for (Player aoeTarget : players) {
                    if (card.isResistible()) {
                        int targetDefenceBonus = aoeTarget.getDefenceBonus();
                        defenceRoll = randomize(1 + targetDefenceBonus, 20 + targetDefenceBonus);
                        if (attackRoll >= defenceRoll) {
                            dmg = card.resist(dmg);
                            aoeTarget.substractHitPoints(dmg);
                        }
                    } else {
                        aoeTarget.substractHitPoints(dmg);
                    }
                }
            }
        } else {
            if (card.isResistible()) {
                int targetDefenceBonus = target.getDefenceBonus();
                defenceRoll = randomize(1 + targetDefenceBonus, 20 + targetDefenceBonus);
                if (attackRoll >= defenceRoll) {
                    dmg = card.resist(dmg);
                    target.substractHitPoints(dmg);
                }
            } else {
                target.substractHitPoints(dmg);
            }
        }
    }

    private void applyEffects(Player target, DefenceCard card) {
        
    }

    private void applyEffects(Player target, ConstructionCard card) {
        if (card.getType().equals("Factory")) {
            players.get(currentPlayer).addFactory(new Factory(card));
        } else if (card.getType().equals("ResearchCenter")) {
            players.get(currentPlayer).setResearchCenter(new ResearchCenter(card));
        }
    }

    private int randomize(int minValue, int maxValue) {
        return minValue + (int) Math.ceil(Math.random() * (maxValue - minValue));
    }
}
