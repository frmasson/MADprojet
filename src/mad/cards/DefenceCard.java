package mad.cards;

import org.json.simple.JSONObject;

/**
 * Une carte de défense.
 * 
 */
public class DefenceCard extends Card {
	private boolean reflective;
	private int cancelPower;

	/**
	 * 
	 * @return vrai si et seulement si cette carte a le pouvoir de réfléchir les
	 *         attaques pouvant être réfléchies.
	 */
	public boolean isReflective() {
		return reflective;
	}

	/**
	 * Configure le pouvoir de réflexion.
	 * 
	 * @param reflective
	 *            vrai si et seulement si cette carte a le pouvoir de réfléchir
	 *            les attaques pouvant être réfléchies
	 */
	public void setReflective(boolean reflective) {
		this.reflective = reflective;
	}

	/**
	 * 
	 * @return la grandeur du pouvoir d’annulation d’une attaque
	 */
	public int getCancelPower() {
		return cancelPower;
	}

	/**
	 * Change la grandeur du pouvoir d’annulation d’une attaque.
	 * 
	 * @param cancelPower
	 *            la nouvelle grandeur du pouvoir d’annulation d’une attaque
	 */
	public void setCancelPower(int cancelPower) {
		this.cancelPower = cancelPower;
	}

	@Override
	public String toString() {
		return "DefenseCard [type()=" + getType() + ", name()=" + getName()
				+ ", image()=" + getImagePath() + ", description="
				+ getDescription() + ", reflective=" + reflective
				+ ", cancelPower=" + cancelPower + "]";
	}

	@Override
	public DefenceCard clone() {
		return (DefenceCard) super.clone();
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		super.fromJson(jsonObject);
		setCancelPower(((Number) jsonObject.get("cancel")).intValue());
		setReflective((Boolean) jsonObject.get("reflect"));
	}
}
