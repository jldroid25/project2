package com.expenseapp.expenseapp.pojo;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class UserPojo {
	private int userId;
	@NotNull
	private String  firstname;
	@NotNull
	private String  lastname;
	@NotNull
	private String  email;
	@NotNull
	private String username;
	@NotNull
	private String  password;
	@NotNull
	private String accessLevel;
	@NotNull
	private boolean userRemoved;

	public UserPojo(int userId, String firstname, String lastname, String email, String username,
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

}