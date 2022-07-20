package com.revature.models;

import java.util.Objects;

public class User {
	private int id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	
	//Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Hash and Equals
	
	@Override
	public int hashCode() {
		return Objects.hash(email, fname, id, lname, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fname, other.fname) && id == other.id
				&& Objects.equals(lname, other.lname) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
	//To String
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fname=" + fname + ", lname="
				+ lname + ", email=" + email + "]";
	}
	
	
	
	
	
}
