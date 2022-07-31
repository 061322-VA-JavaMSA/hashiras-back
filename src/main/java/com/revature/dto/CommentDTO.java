package com.revature.dto;

import java.util.Objects;

import com.revature.models.AnimeComments;
import com.revature.models.User;

public class CommentDTO {

	private int id;
	private int anime_id;
	private User author;
	private String comment;
	
	public CommentDTO(AnimeComments ac) {
		this.id = ac.getCommentId();
		this.anime_id = ac.getAnimeId();
		this.author = ac.getAuthor();
		this.comment = ac.getComment();
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", anime_id=" + anime_id + ", author=" + author + ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(anime_id, author, comment, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDTO other = (CommentDTO) obj;
		return anime_id == other.anime_id && Objects.equals(author, other.author)
				&& Objects.equals(comment, other.comment) && id == other.id;
	}
	
}
