/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.game;

import mad.cards.Card;

/**
 *
 * @author Christophe
 */
public abstract class Construction {

    Card card;

    public Construction(Card card) {
        addCard(card);
    }

    public void addCard(Card card) {
        this.card = card;
    }
}
