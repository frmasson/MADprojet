package mad.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Charge les cartes et les ensembles de cartes.
 * 
 */
public abstract class CardFactory {
	private static Map<String, Class<? extends Card>> CARD_CLASSES;
	private static JSONParser parser;
	private static Map<String, Card> cards;
	private static Map<String, CardSetModel> cardSetModels;

	// Tel que suggéré sur le site officiel de la bibliothèque JSON.simple,
	// on réutilise l’instance de JSONParser.
	private static JSONParser getParser() {
		if (parser == null) {
			parser = new JSONParser();
		}
		return parser;
	}

	private static Object readJsonFile(String path) {
		try {
			InputStream input = CardFactory.class.getResourceAsStream(path);

			if (input == null) {
				throw new RuntimeException("Fichier de configuration « " + path
						+ " » introuvable.");
			}
			return getParser().parse(
					new BufferedReader(new InputStreamReader(input)));
		} catch (IOException e) {
			throw new RuntimeException("Erreur d’entrée-sortie.", e);
		} catch (ParseException e) {
			throw new RuntimeException("Fichier de configuration « " + path
					+ " » corrompu.", e);
		}
	}

	private static Card createCard(JSONObject jsonObject) {
		Card card;
		Class<? extends Card> cardClass;

		if (CARD_CLASSES == null) {
			CARD_CLASSES = new HashMap<String, Class<? extends Card>>();
			CARD_CLASSES.put("Attack", AttackCard.class);
			CARD_CLASSES.put("Defense", DefenseCard.class);
			CARD_CLASSES.put("Factory", ConstructionCard.class);
			CARD_CLASSES.put("ResearchCenter", ConstructionCard.class);
		}

		cardClass = CARD_CLASSES.get(jsonObject.get("type"));
		if (cardClass == null) {
			throw new ClassCastException("Type de carte inconnu : "
					+ jsonObject.get("type").toString());
		}
		try {
			card = cardClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		card.fromJson(jsonObject);
		return card;
	}

	private static Map<String, Card> getCards() {
		if (cards == null) {
			JSONObject map = (JSONObject) readJsonFile("cards.json");
			Iterator<Map.Entry<Object, Object>> i = map.entrySet().iterator();
			Map.Entry<Object, Object> entry;

			while (i.hasNext()) {
				entry = i.next();
				cards.put(entry.getKey().toString(),
						createCard((JSONObject) entry.getValue()));
			}
		}
		return cards;
	}

	private static Map<String, CardSetModel> getCardSetModels() {
		if (cardSetModels == null) {
			JSONArray cardSetList = (JSONArray) readJsonFile("sets.json");

			for (Object item : cardSetList) {
				JSONObject jsonObject = (JSONObject) item;
				CardSetModel model = new CardSetModel();

				model.fromJson(jsonObject);
				cardSetModels.put(model.getName(), model);
			}
		}
		return cardSetModels;
	}

	/**
	 * 
	 * @return l’ensemble des identifiants des cartes qu’il est possible de
	 *         créer
	 */
	public Set<String> getCardIds() {
		return getCards().keySet();
	}

	/**
	 * Crée une nouvelle carte.
	 * 
	 * @param id
	 *            identifiant de la carte
	 * @return la carte
	 */
	public Card getCard(String id) {
		return getCards().get(id).clone();
	}

	/**
	 * 
	 * @return l’ensemble des noms des ensembles de cartes qu’il est possible de
	 *         créer
	 */
	public Set<String> getCardSetNames() {
		return getCardSetModels().keySet();
	}

	/**
	 * Crée un nouveau paquet de cartes.
	 * 
	 * @param name
	 *            le nom de l’ensemble
	 * @return une nouvelle instance de l’ensemble de carte spécifié
	 */
	public CardSet getCardSet(String name) {
		CardSet result = new CardSet();
		CardSetModel model = getCardSetModels().get(name);
		Iterator<Map.Entry<String, Integer>> i = model.iterator();
		Map.Entry<String, Integer> entry;

		result.setName(model.getName());
		while (i.hasNext()) {
			entry = i.next();
			for (int j = entry.getValue(); j > 0; --j) {
				result.add(getCard(entry.getKey()));
			}
		}
		return result;
	}
}
