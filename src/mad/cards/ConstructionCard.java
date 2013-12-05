package mad.cards;

import org.json.simple.JSONObject;

/**
 * Une carte de construction.
 * 
 */
public class ConstructionCard extends Card {
	private int attackFactor;
	private int defenseFactor;

	/**
	 * 
	 * @return le facteur multiplicatif pour une attaque
	 */
	public int getAttackFactor() {
		return attackFactor;
	}

	/**
	 * Change le facteur multiplicatif pour une attaque
	 * 
	 * @param attackFactor
	 *            le nouveau facteur multiplicatif pour une attaque
	 */
	public void setAttackFactor(int attackFactor) {
		this.attackFactor = attackFactor;
	}

	/**
	 * 
	 * @return le facteur multiplicatif pour une défense
	 */
	public int getDefenseFactor() {
		return defenseFactor;
	}

	/**
	 * Change le facteur multiplicatif pour une défense
	 * 
	 * @param defenseFactor
	 *            le nouveau facteur multiplicatif pour une défense
	 */
	public void setDefenseFactor(int defenseFactor) {
		this.defenseFactor = defenseFactor;
	}

	@Override
	public String toString() {
		return "ConstructionCard [type()=" + getType() + ", name()="
				+ getName() + ", image()=" + getImage() + ", description()="
				+ getDescription() + ", attackFactor=" + attackFactor
				+ ", defenseFactor=" + defenseFactor + "]";
	}

	@Override
	public ConstructionCard clone() {
		return (ConstructionCard) super.clone();
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		super.fromJson(jsonObject);
		setAttackFactor(((Number) jsonObject.get("factor-attack")).intValue());
		setDefenseFactor(((Number) jsonObject.get("factor-defense")).intValue());
	}
}
