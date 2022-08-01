package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.CommentDTO;
import com.revature.models.AnimeComments;
import com.revature.services.CommentService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService cs;
	
//	@Autowired
//	private UserService us;
	
	
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
	
	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody AnimeComments comment) {
		
		AnimeComments newComment = cs.createComment(comment);
		
		CommentDTO commentDTO = new CommentDTO(newComment);
		
		return new ResponseEntity<CommentDTO>(commentDTO, HttpStatus.CREATED);
	}

}
