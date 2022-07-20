package com.revature.models;

import java.util.Objects;

public class List {
	private int animeID;
	private User user;
	private int rating;
	private ListStatus status;
	
	//Getters and Setters
	
	public int getAnimeID() {
		return animeID;
	}
	public void setAnimeID(int animeID) {
		this.animeID = animeID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public ListStatus getStatus() {
		return status;
	}
	public void setStatus(ListStatus status) {
		this.status = status;
	}
	
	//Getters and Setters

	@Override
	public int hashCode() {
		return Objects.hash(animeID, rating, status, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		List other = (List) obj;
		return animeID == other.animeID && rating == other.rating && status == other.status
				&& Objects.equals(user, other.user);
	}
	
	//To String
	
	@Override
	public String toString() {
		return "List [animeID=" + animeID + ", user=" + user + ", rating=" + rating + ", status=" + status + "]";
	}
	

	
	
	
}
