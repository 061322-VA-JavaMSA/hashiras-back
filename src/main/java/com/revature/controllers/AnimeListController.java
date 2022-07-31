package com.revature.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.AnimeListDTO;
import com.revature.dto.ReqAnimeListDTO;
import com.revature.exceptions.AnimeListNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;
import com.revature.repositories.AnimeListRepository;
import com.revature.services.AnimeListService;
import com.revature.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anime")
public class AnimeListController {
 
	private AnimeListService as;
	
	
	private UserService us;
	
	private AnimeListRepository alr ;
	@Autowired
	public AnimeListController(UserService us,AnimeListService as) {
		super();
		// TODO Auto-generated constructor stub
		this.us = us;
		this.as = as;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnimeListDTO> getById(@PathVariable("id") int id){
			AnimeListDTO animeDTO = null;
 			
			try {
				animeDTO = new AnimeListDTO(as.getAnimeListById(id));
			} catch (AnimeListNotFoundException e) {
				return new ResponseEntity<>(animeDTO, HttpStatus.NOT_ACCEPTABLE);
			}
			
			return new ResponseEntity<>(animeDTO, HttpStatus.OK);
	}
	
	/* Get Anime List by User ID and Anime ID */
	@GetMapping("/user/{user_id}/anime/{anime_id}")
	public ResponseEntity<AnimeListDTO> findAnimeListByUserAndAnimeId(@PathVariable("user_id") int user_id,@PathVariable("anime_id") int anime_id){
 			AnimeList anime = null;
			User user = new User();
			AnimeListDTO animeDTO = null;
			try {
				user = us.getUserById(user_id);
				
			} catch (UserNotFoundException e) {
				return new ResponseEntity<>(animeDTO, HttpStatus.NOT_ACCEPTABLE);
			}
			if(user != null) {
				anime = as.findAnimeListByUserAndAnimeId(user,anime_id);
			}
			if(anime != null) {
				animeDTO = new AnimeListDTO(anime);

			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			 
			return new ResponseEntity<>(animeDTO, HttpStatus.OK);
	}
	
	/* Get anime by user */
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<AnimeListDTO>> findAnimeListByUser(@PathVariable("user_id") int user_id){
			List<AnimeList> anime = null;
			User user = new User();
			List<AnimeListDTO> animeListDTO =  new ArrayList<>();

			try {
				user = us.getUserById(user_id);
				anime = as.findAnimeListByUser(user);
			} catch (UserNotFoundException e) {
				return new ResponseEntity<>(animeListDTO, HttpStatus.NOT_ACCEPTABLE);
			}
 			for(AnimeList al : anime) {
 				animeListDTO.add(new AnimeListDTO(al));
			}
			 
			 
			return new ResponseEntity<>(animeListDTO, HttpStatus.OK);
	}
	
	/* Add an anime to the user's list */
	@PostMapping
	public ResponseEntity<AnimeListDTO> createAnime(@RequestBody ReqAnimeListDTO reqAnimeListDTO){	
 		User u = new User();
 		AnimeList al = new AnimeList();
 		al.setStatus(ListStatus.DROPPED);
 		al.setUser(u);
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
		return new ResponseEntity<>(animeListDTO, HttpStatus.CONFLICT);	
 	}
 

	 @GetMapping("user/{id}/status/{status}")
	 public ResponseEntity<List<AnimeListDTO>> getListByUserIdAndStatus(@PathVariable("id") int id, @PathVariable("status") String status) throws UserNotFoundException{
		 System.out.println(id);
		 System.out.println(status);
		User u = us.getUserById(id);
	 
		List<AnimeListDTO> animeDTO = new ArrayList<>();
		List<AnimeList> animelist = null;

		animelist = as.findAnimeListByUserAndStatus(u, ListStatus.valueOf(status));

		for(AnimeList al : animelist) {
			animeDTO.add(new AnimeListDTO(al));
		}

		return new ResponseEntity<>(animeDTO, HttpStatus.OK);
		 
	}
 
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Boolean> updateStatusById(@PathVariable("id") int id,@RequestParam(name="status", required=false) String reqStatus){	
	 
		ListStatus status = ListStatus.valueOf(reqStatus)  ;
		int num = as.updateStatusById(status,id);
		boolean bol = (num >= 1) ? true:false;
		return new ResponseEntity<>(bol, HttpStatus.ACCEPTED);	
	}
	
	
	@PutMapping("/{id}/rate")
	public ResponseEntity<Boolean> updateRatingById(@PathVariable("id") int id,@RequestParam(name="user_rating", required=false) int user_rating){

 		if(user_rating <= 0 || user_rating > 10) {
			return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
		}
 		int num = as.updateRatingById(user_rating,id);
		boolean bol = (num >= 1) ? true:false;
		return new ResponseEntity<>(bol, HttpStatus.ACCEPTED);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id){
		AnimeList anime = null;
		try {
			anime = as.getAnimeListById(id);
			
		} catch (AnimeListNotFoundException e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
		}
		if(anime != null) {
			try {
				as.deleteAnimeListById(id);
			} catch (AnimeListNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
 
}
