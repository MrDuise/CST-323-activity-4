package com.gcu.model;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	
	private int userID;
	
	@Valid()
	private CredentialsModel credentials;
	
	@NotNull(message="First Name is a required field")
	@Size(min=1, max=32, message="First Name must be between 1 and 32 characters")
	private String firstName;
	
	@NotNull(message="Last Name is a required field")
	@Size(min=1, max=32, message="Last Name must be between 1 and 32 characters")
	private String lastName;
	
	//@NotNull(message="email is a required field")
	//@Size(min=1, max=32, message="Last Name must be between 1 and 32 characters")
	@Email
	private String email;
	
	@NotNull(message="Phone Number is a required field")
	@Digits(message="Phone number must be 10 digits long with no - symbols", fraction = 0, integer = 10)
	private String phoneNumber;

	/**
	 * 
	 */
	public UserModel() {
		credentials = new CredentialsModel();
	}
	
	
	/**
	 * 
	 * @param userID
	 * @param credentials
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 */

	public UserModel(int userID, CredentialsModel credentials,
			@NotNull(message = "First Name is a required field") @Size(min = 1, max = 32, message = "First Name must be between 1 and 32 characters") String firstName,
			@NotNull(message = "Last Name is a required field") @Size(min = 1, max = 32, message = "Last Name must be between 1 and 32 characters") String lastName,
			@NotNull(message = "email is a required field") @Email(message = "email must be valid") String email,
			@NotNull(message = "Phone Number is a required field") @Digits(message = "Phone number must be 10 digits long", fraction = 0, integer = 10) String phoneNumber) {
		
		this.userID = userID;
		this.credentials = credentials;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	} 


	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public CredentialsModel getCredentials() {
		return credentials;
	}

	
	/**
	 * takes a username and password, then calls the credential model setters
	 * @param userName
	 * @param Password
	 */
	public void setCredentials(String userName, String Password) {
		this.credentials.setUsername(userName); 
		this.credentials.setPassword(Password);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	@Override
	public String toString() {
		return "UserModel [userID=" + userID + ", credentials=" + credentials + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
	

}
