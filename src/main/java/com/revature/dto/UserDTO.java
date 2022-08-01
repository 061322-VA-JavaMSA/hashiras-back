package com.revature.dto;

import java.util.Objects;

import com.revature.models.User;

public class UserDTO {

	private int id;
	private String username;
	private String fname;
	private String lname;
	private String email;
	private int status;
	public UserDTO(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.fname = u.getFname();
		this.lname = u.getLname();
		this.email = u.getEmail();
		this.status = u.getStatus();
	}
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, fname, id, lname, status, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fname, other.fname) && id == other.id
				&& Objects.equals(lname, other.lname) && status == other.status
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
				+ email + ", status=" + status + "]";
	}
	
	
}
