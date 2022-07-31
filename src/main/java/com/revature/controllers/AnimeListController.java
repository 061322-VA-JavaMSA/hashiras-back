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

import com.revature.dto.AnimeListDTO;
import com.revature.dto.ReqAnimeListDTO;
import com.revature.dto.UserDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(AnimeList al : anime) {
				animeDTO.add(new AnimeListDTO(al));
			}
			
			return new ResponseEntity<>(animeDTO, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<AnimeListDTO> createAnime(@RequestBody ReqAnimeListDTO reqAnimeListDTO){	
  
 		AnimeList al = new AnimeList();
		al.setAnimeId(reqAnimeListDTO.getAnime_id());
		try {
			al.setUser(us.getUserById(reqAnimeListDTO.getUser_id()));
		} catch (UserNotFoundException e) {
 			e.printStackTrace();
		}
//		al.setUsercomment(reqAnimeListDTO.getUsercomment());
		al.setUser_rating(reqAnimeListDTO.getUser_rating());
		al.setStatus(ListStatus.valueOf(reqAnimeListDTO.getStatus()));
		AnimeList newAnimeList = as.addAnimeList(al);
		AnimeListDTO animeListDTO = new AnimeListDTO(newAnimeList);
		return new ResponseEntity<>(animeListDTO, HttpStatus.CREATED);
 	}
}
