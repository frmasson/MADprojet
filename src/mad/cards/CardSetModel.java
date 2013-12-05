package mad.cards;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * Les caractéristiques d’un jeu de cartes.
 * 
 * Utilisé par CardSetFactory pour initialiser des jeux de cartes.
 * 
 */
public class CardSetModel {
	private String name;
	private Map<String, Integer> cards = new HashMap<String, Integer>();

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

	/**
	 * Ajoute des cartes au paquet.
	 * 
	 * @param id
	 *            l’identifiant de la carte
	 * @param repetition
	 *            le nombre de copies de la carte
	 */
	public void putCards(String id, int repetition) {
		cards.put(id, repetition);
	}

	/**
	 * 
	 * @return un itérateurs sur des entrées identifiant de la carte -> nombre
	 *         de copies.
	 */
	public Iterator<Map.Entry<String, Integer>> iterator() {
		return cards.entrySet().iterator();
	}

	@Override
	public int hashCode() {
		return (name == null) ? 0 : name.hashCode();
	}

	/**
	 * @param obj l’objet avec lequel comparer
	 * @return vrai si et seulement si obj est de même classe et possède le même nom
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		CardSetModel other = (CardSetModel) obj;
		
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * Charge les valeurs des propriétés depuis un objet JSON.
	 * 
	 * @param jsonObject
	 *            l’objet JSON source
	 */
	public void fromJson(JSONObject jsonObject) {
		JSONObject cardsJson = (JSONObject) jsonObject.get("cards");
		Iterator<Map.Entry<Object, Object>> i = cardsJson.entrySet().iterator();
		Map.Entry<Object, Object> entry;

		setName(jsonObject.get("name").toString());
		while (i.hasNext()) {
			entry = i.next();
			putCards(entry.getKey().toString(),
					((Number) entry.getValue()).intValue());
		}
	}
}
