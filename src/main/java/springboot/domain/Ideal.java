package springboot.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * The Ideal class represents the ideal personality of the player.
 * </p>
 * 
 * @author chenyu
 *
 */
@Entity
public class Ideal implements Serializable{
	private static final long serialVersionUID = -162671173711901751L;

	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "openess", columnDefinition = "DOUBLE")
	private double openess = 0;

	@Column(name = "conscientiousness", columnDefinition = "DOUBLE")
	private double conscientiousness = 0;

	@Column(name = "extraversion", columnDefinition = "DOUBLE")
	private double extraversion = 0;

	@Column(name = "agreeableness", columnDefinition = "DOUBLE")
	private double agreeableness = 0;

	@Column(name = "emotionalrange", columnDefinition = "DOUBLE")
	private double emotionalrange = 0; // neuroticism

	@Column(name = "auth", columnDefinition = "BOOLEAN")
	private boolean auth = false;
	
	/**
	 * Constructor
	 */
	public Ideal() {
	}

	/**
	 * Constructor
	 * 
	 * @param id id
	 */
	public Ideal(String id) {
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param openess           the degree of openess
	 * @param conscientiousness the degree of conscientiousness
	 * @param extraversion      the degree of extraversion
	 * @param agreeableness     the degree of agreeableness
	 * @param emotionalrange    the degree of emotionalrange
	 */
	public Ideal(double openess, double conscientiousness, double extraversion, double agreeableness,
			double emotionalrange) {
		this.openess = openess;
		this.conscientiousness = conscientiousness;
		this.extraversion = extraversion;
		this.agreeableness = agreeableness;
		this.emotionalrange = emotionalrange;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param openess
	 * @param conscientiousness
	 * @param extraversion
	 * @param agreeableness
	 * @param emotionalrange
	 */
	public Ideal(String id, double openess, double conscientiousness, double extraversion, double agreeableness,
			double emotionalrange) {
		this(openess, conscientiousness, extraversion, agreeableness, emotionalrange);
		this.setId(id);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the openess
	 */
	public double getOpeness() {
		return openess;
	}

	/**
	 * @param openess the openess to set
	 */
	public void setOpeness(double openess) {
		this.openess = openess;
	}

	/**
	 * @return the conscientiousness
	 */
	public double getConscientiousness() {
		return conscientiousness;
	}

	/**
	 * @param conscientiousness the conscientiousness to set
	 */
	public void setConscientiousness(double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	/**
	 * @return the extraversion
	 */
	public double getExtraversion() {
		return extraversion;
	}

	/**
	 * @param extraversion the extraversion to set
	 */
	public void setExtraversion(double extraversion) {
		this.extraversion = extraversion;
	}

	/**
	 * @return the agreeableness
	 */
	public double getAgreeableness() {
		return agreeableness;
	}

	/**
	 * @param agreeableness the agreeableness to set
	 */
	public void setAgreeableness(double agreeableness) {
		this.agreeableness = agreeableness;
	}

	/**
	 * @return the emotionalrange
	 */
	public double getEmotionalrange() {
		return emotionalrange;
	}

	/**
	 * @param emotionalrange the emotionalrange to set
	 */
	public void setEmotionalrange(double emotionalrange) {
		this.emotionalrange = emotionalrange;
	}

	/**
	 * @return the auth
	 */
	public boolean isAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ideal [id=" + id + ", openess=" + openess + ", conscientiousness=" + conscientiousness
				+ ", extraversion=" + extraversion + ", agreeableness=" + agreeableness + ", emotionalrange="
				+ emotionalrange + ", auth=" + auth + "]";
	}
}
