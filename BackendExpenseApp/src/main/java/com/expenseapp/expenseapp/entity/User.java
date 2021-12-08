package com.expenseapp.expenseapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

//Add The mandatory @Entity annotation
@Entity

/*
 * Provide the database table for the data Adding the @Table step is optional if
 * the entity class names and the table name match. However, it is preferred to
 * specify it even if Entity & table names in db match.
 * 
 */
@Table(name = "user_site_data")

public class User {
	// Hibernate expects us to specify the primary key column
	@Id

	// we specify the primary key auto generation strategy used in the underlying DB
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// @Column is optional if the variable name and the column names match.
	// However, it is always preferred to specify them even if variable & column
	// names in db match.
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column (name = "password")
	private String password;
	
	@Column(name = "access_level")
	private String accessLevel;
	
	@Column(name = "user_removed")
	private boolean userRemoved;

	public User() {
		super();
	}

	public User(int userId, String firstname, String lastname, String email, String username,
			String password, String accessLevel, boolean userRemoved) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
		this.userRemoved = userRemoved;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public boolean isUserRemoved() {
		return userRemoved;
	}

	public void setUserRemoved(boolean userRemoved) {
		this.userRemoved = userRemoved;
	}

	@Override
	public String toString() {
		return "UserInformation [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", username=" + username + ", password=" + password + ", accessLevel=" + accessLevel
				+ ", userRemoved=" + userRemoved + "]";
	}

}
