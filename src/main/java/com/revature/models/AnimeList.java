package com.revature.models;

 import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="lists")
public class AnimeList implements Serializable  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "anime_id")
 	private int animeId;
	 
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name = "user_id")		
	private User user;
    
    @Column
	private int user_rating;
    
	@Enumerated(EnumType.STRING)
	private ListStatus status;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public int getAnimeId() {
		return animeId;
	}

	public void setAnimeId(int animeId) {
		this.animeId = animeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animeId, id, status, user, user_rating);
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
		return animeId == other.animeId && id == other.id && status == other.status && Objects.equals(user, other.user)
				&& user_rating == other.user_rating;
	}

	@Override
	public String toString() {
		return "AnimeList [id=" + id + ", animeId=" + animeId + ", user=" + user + ", user_rating=" + user_rating
				+ ", status=" + status + "]";
	}

 
 
}
