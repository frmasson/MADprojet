/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.game;


import mad.cards.ConstructionCard;

/**
 *
 * @author Christophe
 */
public class Factory extends Construction {

    private int attackFactor;

    public Factory(ConstructionCard card) {
        super(card);
        this.attackFactor = card.getAttackFactor();
    }

    public int getAttackFactor() {
        return attackFactor;
    }

    public void setAttackFactor(int attackFactor) {
        this.attackFactor = attackFactor;
    }
    
}
