package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ideal {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;
	
	@Column(name = "att1", columnDefinition = "INT")
	private int att1;
	
	@Column(name = "att2", columnDefinition = "INT")
	private int att2;
	
	@Column(name = "att3", columnDefinition = "INT")
	private int att3;
	
	@Column(name = "att4", columnDefinition = "INT")
	private int att4;
	
	@Column(name = "att5", columnDefinition = "INT")
	private int att5;

	// TODO Constructor
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAtt1() {
		return att1;
	}

	public void setAtt1(int att1) {
		this.att1 = att1;
	}

	public int getAtt2() {
		return att2;
	}

	public void setAtt2(int att2) {
		this.att2 = att2;
	}

	public int getAtt3() {
		return att3;
	}

	public void setAtt3(int att3) {
		this.att3 = att3;
	}

	public int getAtt4() {
		return att4;
	}

	public void setAtt4(int att4) {
		this.att4 = att4;
	}

	public int getAtt5() {
		return att5;
	}

	public void setAtt5(int att5) {
		this.att5 = att5;
	}
}
