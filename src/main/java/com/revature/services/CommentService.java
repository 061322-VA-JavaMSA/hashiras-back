package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.AnimeComments;
import com.revature.models.User;
import com.revature.repositories.CommentRepository;

@Service
public class CommentService {
	private CommentRepository cr;
	
	public CommentService(CommentRepository cr) {
		super();
		this.cr = cr;
	}
	
	public List<AnimeComments> getAllComments() {
		return cr.findAll();
	}
	
	@Transactional
	public List<AnimeComments> findCommentsByAnimeId(int id) {
		List<AnimeComments> comments = cr.findAnimeCommentsByAnimeId(id);
		return comments;
	}
	
	@Transactional
	public AnimeComments createComment(AnimeComments comment) {
		return cr.save(comment);
	}
//	@Transactional
//	public List<AnimeComments> findCommentsByAnimeIdAndUser(int id, User user) {
//		List<AnimeComments> comments = cr.findAnimeCommentsByAnimeIdAndUser(id, user);
//		return comments;
//	}
	
}
