/**
 * 
 */
package com.projectab.app.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projectab.app.validation.ValidEmail;
import com.projectab.app.validation.ValidPassword;

/**
 * @author Bhupesh
 *
 */
public class UserDto {

	@NotNull
	@Size(min=3,max=30, message="Size should be between 3 and 30")
	private String username;
	@NotNull
	@ValidPassword
	private String password;
	@ValidPassword
	private String matchingPassword;
	@NotNull
	@ValidEmail
	private String email;
	private String name;
	private Date dateRegistered;
	private String status;
	@NotNull
	@Size(min=3)
	private String firstname;
	@NotNull
	@Size(min=3)
	private String lastname;
	private String contactNumber;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
