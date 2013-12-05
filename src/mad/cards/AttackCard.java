package mad.cards;

import org.json.simple.JSONObject;

/**
 * Une carte d’attaque.
 * 
 */
public class AttackCard extends Card {
	private int damageMin;
	private int damageMax;
	private float resistance;
	private boolean reflexible;
	private int cancelable;
	private boolean areaOfEffect;

	/**
	 * 
	 * @return le pointage minimal retourné par getDamage()
	 */
	public int getDamageMin() {
		return damageMin;
	}

	/**
	 * Change le pointage minimal retourné par getDamage()
	 * 
	 * @param damageMin
	 *            le nouveau pointage minimal retourné par getDamage()
	 */
	public void setDamageMin(int damageMin) {
		this.damageMin = damageMin;
	}

	/**
	 * 
	 * @return le pointage maximal retourné par getDamage()
	 */
	public int getDamageMax() {
		return damageMax;
	}

	/**
	 * Change le pointage maximal retourné par getDamage()
	 * 
	 * @param damageMax
	 *            le nouveau pointage maximal retourné par getDamage()
	 */
	public void setDamageMax(int damageMax) {
		this.damageMax = damageMax;
	}

	/**
	 * 
	 * @return la résistance qu’il est possible d’appliquer sur cette carte
	 *         suite à l’obtention d’un jet de défense plus grand que le jet
	 *         d’attaque. 1 signifie une résistance totale.
	 */
	public float getResistance() {
		return resistance;
	}

	/**
	 * Change la résistance qu’il est possible d’appliquer sur cette carte suite
	 * à l’obtention d’un jet de défense plus grand que le jet d’attaque.
	 * 
	 * 1 signifie une résistance totale.
	 * 
	 * @param resistance
	 *            la nouvelle résistance qu’il est possible d’appliquer sur
	 *            cette carte.
	 */
	public void setResistance(float resistance) {
		this.resistance = resistance;
	}

	/**
	 * 
	 * @return vrai si et seulement si une partie adverse peut réfléchir
	 *         l’attaque
	 */
	public boolean isReflexible() {
		return reflexible;
	}

	/**
	 * Configure le caractère réflexible de l’attaque.
	 * 
	 * @param reflexible
	 *            vrai si et seulement si une partie adverse peut réfléchir
	 *            l’attaque
	 */
	public void setReflexible(boolean reflexible) {
		this.reflexible = reflexible;
	}

	/**
	 * 
	 * @return la puissance d’annulation nécessaire pour annuler l’attaque
	 */
	public int getCancelable() {
		return cancelable;
	}

	/**
	 * Change la puissance d’annulation nécessaire pour annuler l’attaque
	 * 
	 * @param cancelable
	 *            la nouvelle puissance d’annulation nécessaire pour annuler
	 *            l’attaque
	 */
	public void setCancelable(int cancelable) {
		this.cancelable = cancelable;
	}

	/**
	 * 
	 * @return vrai si et seulement si l’attaque peut s’appliquer à tous les
	 *         adversaires. Si faux, seul un joueur peut être attaqué avec cette
	 *         carte.
	 */
	public boolean isAreaOfEffect() {
		return areaOfEffect;
	}

	/**
	 * Configure le rayon d’action de l’attaque.
	 * 
	 * @param areaOfEffect
	 *            vrai si et seulement si l’attaque peut s’appliquer à tous les
	 *            adversaires. Si faux, seul un joueur peut être attaqué avec
	 *            cette carte.
	 */
	public void setAreaOfEffect(boolean areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}

	/**
	 * 
	 * @return les dégâts à envoyer à l’adversaire, sous la forme d’un pointage
	 *         positif.
	 * 
	 *         Note : Le pointage retourné peut être aléatoire.
	 */
	public int getDamage() {
		return damageMin
				+ (int) Math.ceil(Math.random() * (damageMax - damageMin));
	}

	/**
	 * 
	 * @return vrai si et seulement si il est possible de résister à cette carte
	 *         (en tout ou en partie) suite à un jet aléatoire de défense
	 */
	public boolean isResistible() {
		return resistance > .0f;
	}

	/**
	 * Calcule le nouveau pointage des dégâts à envoyer à l’adversaire suite
	 * l’obtention, par l’adversaire, d’une résistance à l’aide d’un aléatoire
	 * de défense.
	 * 
	 * Une résistance ce gagne de façon aléatoire.
	 * 
	 * @param initialDamage
	 *            les dégâts initialement envoyés, sous la forme d’un pointage
	 *            positif
	 * @return le nouveau pointage pour les dégâts
	 */
	public int resist(int initialDamage) {
		return (int) Math.ceil(initialDamage * (1.0 - resistance));
	}

	/**
	 * 
	 * @return vrai si et seulement si il est possible d’annuler cette carte à
	 *         l’aide d’une carte de défense
	 */
	public boolean isCancelable() {
		return cancelable > 0;
	}

	/**
	 * Calcule le nouveau pointage des dégâts à envoyer à l’adversaire suite
	 * l’application, par l’adversaire, d’une ou plusieurs cartes de défense.
	 * 
	 * @param initialDamage
	 *            les dégâts initialement envoyés, sous la forme d’un pointage
	 *            positif
	 * @param cancelPower
	 *            la puissance de l’annulation. Si elle est insuffisante,
	 *            l’annulation échoue.
	 * @return le nouveau pointage pour les dégâts
	 */
	public int cancel(int initialDamage, int cancelPower) {
		if (cancelable - cancelPower < 0 && cancelable != 0) {
			return 0;
		} else {
			return initialDamage;
		}
	}

	@Override
	public String toString() {
		return "AttackCard [type=" + getType() + ", name=" + getName()
				+ ", image=" + getImage() + ", description=" + getDescription()
				+ ", damageMin=" + damageMin + ", damageMax=" + damageMax
				+ ", resistance=" + resistance + ", reflexible=" + reflexible
				+ ", cancelable=" + cancelable + ", areaOfEffect="
				+ areaOfEffect + "]";
	}

	@Override
	public AttackCard clone() {
		return (AttackCard) super.clone();
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		super.fromJson(jsonObject);
		setAreaOfEffect((Boolean) jsonObject.get("area-of-effect"));
		setCancelable(((Number) jsonObject.get("cancelable")).intValue());
		setDamageMax(((Number) jsonObject.get("damage-max")).intValue());
		setDamageMin(((Number) jsonObject.get("damage-min")).intValue());
		setReflexible((Boolean) jsonObject.get("reflexible"));
		setResistance(((Number) jsonObject.get("resistance")).floatValue());
	}
}
