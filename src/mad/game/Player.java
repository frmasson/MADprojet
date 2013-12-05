/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.game;

import java.util.ArrayList;
import mad.cards.Card;

/**
 *
 * @author Christophe
 */
public abstract class Player {
    private String name;
    private int hitPoints = 50;
    private ResearchCenter researchCenter;
    private ArrayList <Factory> factories;
    private ArrayList <Card> cards;
    
    private final int NBMAXFACTORIES = 2;
    private final int NBMAXCARDS = 5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

        
    public void addHitPoints(int hitPoints) {
        if (hitPoints >0)
            this.hitPoints += hitPoints;
    }
    
    public void substractHitPoints(int hitPoints) {
        if (hitPoints >0)
            this.hitPoints -= hitPoints;
    }

    public ResearchCenter getResearchCenter() {
        return researchCenter;
    }

    public void setResearchCenter(ResearchCenter researchCenter) {
        this.researchCenter = researchCenter;
    }

    public ArrayList<Factory> getFactories() {
        return factories;
    }

    public void ajouterFactory(Factory factory) {
        if (factories.size() < NBMAXFACTORIES){
            factories.add(factory);
        } else {
            //@TODO le joueur doit retirer une de ses anciennes usines
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    
    public void addCard(Card card){
        if (cards.size() < NBMAXCARDS){
            cards.add(card);
        }
    }
    
    public void discardCard(Card card){
        cards.remove(card);
        //@TODO mettre la carte dans le paquet du talon
    }
    
    public void playCard(Card card){
        //card.play();
        //@TODO ???
        discardCard(card);
    }
    
    
    
        
}
