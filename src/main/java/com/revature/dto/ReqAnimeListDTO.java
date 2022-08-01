package com.revature.dto;

import java.util.Objects;

import com.revature.models.User;

public class ReqAnimeListDTO {
	/**
	 * @param id
	 * @param anime_id
	 * @param user_id
	 * @param usercomment
	 * @param user_rating
	 * @param status
	 */
 
	private int id;
 	private int anime_id;
	private int user_id;
	private String usercomment;
	private int user_rating;
	private String status;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
		return "ReqAnimeListDTO [id=" + id + ", anime_id=" + anime_id + ", user_id=" + user_id + ", usercomment="
				+ usercomment + ", user_rating=" + user_rating + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(anime_id, id, status, user_id, user_rating, usercomment);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReqAnimeListDTO other = (ReqAnimeListDTO) obj;
		return anime_id == other.anime_id && id == other.id && Objects.equals(status, other.status)
				&& user_id == other.user_id && user_rating == other.user_rating
				&& Objects.equals(usercomment, other.usercomment);
	}
	
}
