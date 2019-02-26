package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ideal {
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
	
	@Column(name = "jsonresult", columnDefinition = "TEXT")
	public String jsonResult = null;

	// TODO Constructor
	public Ideal() {
	}
	
	public Ideal(String id) {
		this.id = id;
	}
	
	public Ideal(double openess, double conscientiousness, double extraversion, double agreeableness,
			double emotionalrange) {
		this.openess = openess;
		this.conscientiousness = conscientiousness;
		this.extraversion = extraversion;
		this.agreeableness = agreeableness;
		this.emotionalrange = emotionalrange;
	}
	
	public Ideal(String id, double openess, double conscientiousness, double extraversion, double agreeableness,
			double emotionalrange) {
		this(openess, conscientiousness, extraversion, agreeableness, emotionalrange);
		this.setId(id);
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getOpeness() {
		return openess;
	}

	public void setOpeness(double openess) {
		this.openess = openess;
	}

	public double getConscientiousness() {
		return conscientiousness;
	}

	public void setConscientiousness(double conscientiousness) {
		this.conscientiousness = conscientiousness;
	}

	public double getExtraversion() {
		return extraversion;
	}

	public void setExtraversion(double extraversion) {
		this.extraversion = extraversion;
	}

	public double getAgreeableness() {
		return agreeableness;
	}

	public void setAgreeableness(double agreeableness) {
		this.agreeableness = agreeableness;
	}

	public double getEmotionalrange() {
		return emotionalrange;
	}

	public void setEmotionalrange(double emotianlrange) {
		this.emotionalrange = emotianlrange;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	@Override
	public String toString() {
		return "Ideal [id=" + id + ", openess=" + openess + ", conscientiousness=" + conscientiousness
				+ ", extraversion=" + extraversion + ", agreeableness=" + agreeableness + ", emotionalrange="
				+ emotionalrange + ", jsonResult=" + jsonResult + "]";
	}
}
