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
public class ResearchCenter extends Construction{
     private int defenceFactor;
     private int attackFactor;
     
     public ResearchCenter(ConstructionCard card){
         super(card);
         this.attackFactor = card.getAttackFactor();
         this.defenceFactor = card.getDefenseFactor();
     }

    public int getDefenceFactor() {
        return defenceFactor;
    }

    public void setDefenceFactor(int defenceFactor) {
        this.defenceFactor = defenceFactor;
    }

    public int getAttackFactor() {
        return attackFactor;
    }

    public void setAttackFactor(int attackFactor) {
        this.attackFactor = attackFactor;
    }
     
}
