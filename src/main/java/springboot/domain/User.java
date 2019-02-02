package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;
	
	@NotNull(message = "Username cannot be null")
	@Length(min = 1, max = 15, message = "Username should be 1-15 characters")
	@Column(name = "username", columnDefinition = "VARCHAR(15)")
	private String username;

	@NotNull(message = "Password cannot be null")
	@Length(min = 1, max = 15, message = "Password should be 1-15 characters")
	@Column(name = "password", columnDefinition = "VARCHAR(15)")
	private String password;

	@Column(name = "token")
	private String accessToken;
	
	@Column(name = "secret")
	private String accessTokenSecret;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User(String id, String username, String password, String accessToken, String accessTokenSecret) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
}