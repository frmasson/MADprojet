package mad.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Un jeu de cartes.
 * 
 */
public class CardSet implements Collection<Card> {
	private String name;
	private List<Card> cards = new ArrayList<Card>();
        
        

	/**
	 * 
	 * @return le nom du jeu de cartes ou null si le jeu n’est pas nommé
	 */
	public String getName() {
		return name;
	}

	/**
	 * Configure le nom du jeu de cartes
	 * 
	 * @param name
	 *            le nouveau nom du jeu de cartes
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean add(Card card) {
		return cards.add(card);
	}

	@Override
	public boolean addAll(Collection<? extends Card> cards) {
		return this.cards.addAll(cards);
	}

	@Override
	public void clear() {
		this.cards.clear();
	}

	@Override
	public boolean contains(Object card) {
		return this.cards.contains(card);
	}

	@Override
	public boolean containsAll(Collection<?> cards) {
		return this.cards.containsAll(cards);
	}

	@Override
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}

	@Override
	public Iterator<Card> iterator() {
		return this.cards.iterator();
	}

	@Override
	public boolean remove(Object card) {
		return this.cards.remove(card);
	}

	@Override
	public boolean removeAll(Collection<?> cards) {
		return this.cards.removeAll(cards);
	}

	@Override
	public boolean retainAll(Collection<?> cards) {
		return this.cards.removeAll(cards);
	}

	@Override
	public int size() {
		return this.cards.size();
	}

	@Override
	public Object[] toArray() {
		return this.cards.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.cards.toArray(a);
	}

	/**
	 * Brasse le jeu de cartes.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Retire et retourne la carte au sommet du paquet de cartes (la dernière)
	 * 
	 * @return la carte au sommet du paquet (la dernière)
	 * @throws IllegalStateException
	 *             Le paquet est vide.
	 */
	public Card pop() {
		if (this.cards.isEmpty()) {
			throw new IllegalStateException(
					"Il ne reste aucune carde à distribuer.");
		}
		return this.cards.remove(this.cards.size() - 1);
	}

	@Override
	public String toString() {
		return "CardSet [name=" + name + ", cards=" + cards + "]";
	}
}
