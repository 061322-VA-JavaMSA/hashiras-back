package com.revature.dto;

import java.util.Objects;

import com.revature.models.AnimeList;
import com.revature.models.User;

public class AnimeListDTO {

	private int id;
 	private int anime_id;
	private User user;
	private String usercomment;
	private int user_rating;
	private String status;
	
	public AnimeListDTO(AnimeList al) {
		this.id = al.getId();
		this.anime_id = al.getAnimeId();
		this.user = al.getUser();
		this.usercomment = al.getUsercomment();
		this.user_rating = al.getUser_rating();
		this.status = al.getStatus().toString();
	}
 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAnime_id() {
		return anime_id;
	}


	public void setAnime_id(int anime_id) {
		this.anime_id = anime_id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "AnimeListDTO [id=" + id + ", anime_id=" + anime_id + ", user=" + user + ", usercomment=" + usercomment
				+ ", user_rating=" + user_rating + ", status=" + status + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(anime_id, id, status, user, user_rating, usercomment);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeListDTO other = (AnimeListDTO) obj;
		return anime_id == other.anime_id && id == other.id && Objects.equals(status, other.status)
				&& Objects.equals(user, other.user) && user_rating == other.user_rating
				&& Objects.equals(usercomment, other.usercomment);
	}
}
