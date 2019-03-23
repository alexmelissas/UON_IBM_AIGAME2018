package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * The User class represents the account of the user
 * </p>
 * 
 * @author Yu Chen
 *
 */
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

	/**
	 * Constructor
	 */
	public User() {
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param username
	 * @param password
	 */
	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param username
	 * @param password
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public User(String id, String username, String password, String accessToken, String accessTokenSecret) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the access Token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the access Token to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the access Token Secret
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 * @param accessTokenSecret the access Token Secret to set
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", accessToken=" + accessToken
				+ ", accessTokenSecret=" + accessTokenSecret + "]";
	}
}