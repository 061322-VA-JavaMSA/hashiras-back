package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lists")
public class AnimeList {
	@Column
	private int anime_id;
	
    @ManyToOne
    @JoinColumn(name = "id")	
	private User user;
    
    @Column
	private String usercomment;
	
    @Column
	private int user_rating;
    
	@Enumerated(EnumType.STRING)
	private ListStatus status;
	
	//Getters and Setters
	
 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getAnime_id() {
		return anime_id;
	}
	public void setAnime_id(int anime_id) {
		this.anime_id = anime_id;
	}
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public int getUser_rating() {
		return user_rating;
	}
	public void setUser_rating(int user_rating) {
		this.user_rating = user_rating;
	}
	public ListStatus getStatus() {
		return status;
	}
	public void setStatus(ListStatus status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(anime_id, status, user, user_rating, usercomment);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeList other = (AnimeList) obj;
		return anime_id == other.anime_id && status == other.status && Objects.equals(user, other.user)
				&& user_rating == other.user_rating && Objects.equals(usercomment, other.usercomment);
	}
	@Override
	public String toString() {
		return "AnimeList [anime_id=" + anime_id + ", user=" + user + ", usercomment=" + usercomment + ", user_rating="
				+ user_rating + ", status=" + status + "]";
	}
 
	//Getters and Setters

	 
	

	
	
	
}
