package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="comments")
public class AnimeComments implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;
	
	@Column(name="anime_id")
	private int animeId;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name = "author")	
	private User author;
	
	@Column(name="comment")
	private String comment;
	
	//Variables
	
	public int getAnimeId() {
		return animeId;
	}
	public void setAnimeId(int animeId) {
		this.animeId = animeId;
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
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	//To String 
	
	@Override
	public String toString() {
		return "AnimeComments [commentId=" + commentId + ", animeId=" + animeId + ", commenter=" + author
				+ ", comment=" + comment + "]";
	}
	
	//Hash Equals
	
	@Override
	public int hashCode() {
		return Objects.hash(animeId, comment, commentId, author);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeComments other = (AnimeComments) obj;
		return animeId == other.animeId && Objects.equals(comment, other.comment) && commentId == other.commentId
				&& Objects.equals(author, other.author);
	}
	

	
	


	

	
	
	
}
