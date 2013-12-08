package mad.cards;

import java.net.URL;

import javax.swing.ImageIcon;

import org.json.simple.JSONObject;

/**
 * Une carte du jeu.
 * 
 */
public abstract class Card implements Cloneable {
	private String type;
	private String name;
	private String imagePath;
	private ImageIcon image;
	private String description;

	/**
	 * 
	 * @return le type spécifique de carte. Utilisé dans certaines règles du jeu
	 *         pour limiter les cartes disponibles.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Change le type spécifique de carte.
	 * 
	 * Utilisé dans certaines règles du jeu pour limiter les cartes disponibles.
	 * 
	 * @param type
	 *            le nouveau type spécifique de carte
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return le nom de la carte
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change le nom de la carte.
	 * 
	 * @param name
	 *            le nouveau nom de la carte
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return le chemin d’accès de l’image
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Change le chemin d’accès de l’image.
	 * 
	 * @param imagePath
	 *            le nouveau chemin d’accès de l’image
	 */
	public void setImagePath(String imagePath) {
		this.image = null;
		this.imagePath = imagePath;
	}
	
	/**
	 * 
	 * @return l’image de la carte.
	 */
	public ImageIcon getImage() {
		if (image == null) {
			URL url = Card.class.getResource(imagePath);
			
			if (url == null) {
				throw new RuntimeException("Image introuvable.");
			} else {
				image = new ImageIcon(url);
			}
		}
		return image;
	}

	/**
	 * 
	 * @return la description textuelle
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change la description textuelle.
	 * 
	 * @param description
	 *            la nouvelle description textuelle
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Card clone() {
		try {
			return (Card) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Charge les valeurs des propriétés depuis un objet JSON.
	 * 
	 * @param jsonObject
	 *            l’objet JSON source
	 */
	public void fromJson(JSONObject jsonObject) {
		setType(jsonObject.get("type").toString());
		setName(jsonObject.get("name").toString());
		setImagePath(jsonObject.get("image").toString());
		setDescription(jsonObject.get("description").toString());
	}
}
