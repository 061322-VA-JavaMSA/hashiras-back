package com.revature.controllers;

import java.io.Console;
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

import com.revature.dto.AnimeListDTO;
import com.revature.dto.ReqAnimeListDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;
import com.revature.services.AnimeListService;
import com.revature.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anime")
public class AnimeListController {
	@Autowired
	private AnimeListService as;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<AnimeListDTO>> getUserById(@PathVariable("id") int id){
			List<AnimeListDTO> animeDTO = new ArrayList<>();
			List<AnimeList> anime = null;
			
			try {
				anime = as.findAnimeListByUser(us.getUserById(id));
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
			
			for(AnimeList al : anime) {
				animeDTO.add(new AnimeListDTO(al));
			}
			
			return new ResponseEntity<>(animeDTO, HttpStatus.OK);
	}
	
	/* Get Anime List by User ID and Anime ID */
	@GetMapping("/user/{user_id}/anime/{anime_id}")
	public ResponseEntity<AnimeList> findAnimeListByUserAndAnimeId(@PathVariable("user_id") int user_id,@PathVariable("anime_id") int anime_id){
 			AnimeList anime = null;
			User user = new User();
 			
			try {
				user = us.getUserById(user_id);
				anime = as.findAnimeListByUserAndAnimeId(user,anime_id);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
			
 
			return new ResponseEntity<>(anime, HttpStatus.OK);
	}
	
	/* Get anime by user */
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<AnimeList>> findAnimeListByUser(@PathVariable("user_id") int user_id){
			List<AnimeList> anime = null;
			User user = new User();
 			
			try {
				user = us.getUserById(user_id);
				anime = as.findAnimeListByUser(user);
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			}
			
 
			return new ResponseEntity<>(anime, HttpStatus.OK);
	}
	
	/* Add an anime to the user's list */
	@PostMapping
	public ResponseEntity<AnimeListDTO> createAnime(@RequestBody ReqAnimeListDTO reqAnimeListDTO){	
 		User u = new User();
 		AnimeList al = new AnimeList();
 		al.setStatus(ListStatus.DROPPED);
 		AnimeListDTO animeListDTO = new AnimeListDTO(al);
 		try {
			u = us.getUserById(reqAnimeListDTO.getUser_id());
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(animeListDTO, HttpStatus.NOT_ACCEPTABLE);
		}
 		
 	 
 		if(u.getId() != 0) {
 				AnimeList animeList = as.findAnimeListByUserAndAnimeId(u, reqAnimeListDTO.getAnime_id()); 
 				if(animeList == null) {
					al.setAnimeId(reqAnimeListDTO.getAnime_id());
					al.setUser(u);
					al.setUser_rating(reqAnimeListDTO.getUser_rating());
					al.setStatus(ListStatus.valueOf(reqAnimeListDTO.getStatus()));
					AnimeList newAnimeList = as.addAnimeList(al);
					animeListDTO = new AnimeListDTO(newAnimeList);
					return new ResponseEntity<>(animeListDTO, HttpStatus.CREATED);
 				}
		}
		return new ResponseEntity<>(animeListDTO, HttpStatus.NOT_ACCEPTABLE);	
 	}
}
