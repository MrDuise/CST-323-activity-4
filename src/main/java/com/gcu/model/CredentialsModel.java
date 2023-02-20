package com.gcu.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

public class CredentialsModel {

	@NotNull(message = "User name is a required field")
	@Size(min = 1, max = 32, message = "User name must be between 1 and 32 characters")
	private String username;

	@NotNull(message = "Password is a required field")
	@Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters")
	private String password;

	public CredentialsModel(
            @NotNull(message = "User name is a required field") @Size(min = 1, max = 32, message = "User name must be between 1 and 32 characters") String username,
            @NotNull(message = "Password is a required field") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters") String password) {
        super();
        this.username = username;
        this.password = password;
    }
	
	public CredentialsModel() {
	    
	}

    public CredentialsModel(String username2, String password2, List<GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "CredentialsModel [username=" + username + ", password=" + password + "]";
	}

}