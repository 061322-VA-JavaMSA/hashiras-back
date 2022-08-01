package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.CommentDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.AnimeComments;
import com.revature.services.CommentService;
import com.revature.services.UserService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService cs;
	
	@Autowired
	private UserService us;
	
	
	@GetMapping("/{anime_id}")
	public ResponseEntity<List<CommentDTO>> getCommentsById(@PathVariable("anime_id") int id) {
		List<CommentDTO> commentDTO = new ArrayList<>();
		List<AnimeComments> comments = null;
		
		comments = cs.findCommentsByAnimeId(id);
		
		for(AnimeComments ac: comments) {
			commentDTO.add(new CommentDTO(ac));
		}
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CommentDTO> createComment(@RequestParam(name = "animeId") String animeId, @RequestParam(name = "author") String userId,  @RequestParam(name = "comment") String comment) {
		System.out.println("Im here");
		AnimeComments newComment = new AnimeComments();
		newComment.setAnimeId(Integer.valueOf(animeId));
		try {
			newComment.setAuthor(us.getUserById(Integer.valueOf(userId)));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newComment.setComment(comment);
		
		cs.createComment(newComment);
		
		
		CommentDTO commentDTO = new CommentDTO(newComment);
		
		return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.CREATED);
	}

}
