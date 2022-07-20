package com.revature.services;

import java.util.List;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.revature.exceptions.AnimeListNotFoundException;
import com.revature.models.AnimeList;
import com.revature.models.User;
import com.revature.repositories.AnimeListRepository;

@Service
public class AnimeListService {
	
 

	private AnimeListRepository alr;
	
	public AnimeListService(AnimeListRepository alr) {
		super();
		this.alr = alr;
	}
	
	public List<AnimeList> getAnimeList(){
		return alr.findAll();
	}
 
	
	@Transactional
	public AnimeList getAnimeListById(int id) throws AnimeListNotFoundException {
		AnimeList animelist = alr.findById(id).orElseThrow(() -> new AnimeListNotFoundException());
		return animelist;
	}
	
	@Transactional
	public List<AnimeList> findAnimeListByUser(User user) {
		List<AnimeList> animelist = alr.findAnimeListByUser(user);
		return animelist;
	}

	@Transactional
	public List<AnimeList> findAnimeListByUserAnimeId(User user,int anime_id) {
		List<AnimeList> animelist = alr.findAnimeListByUserAnimeId(user,anime_id);
		return animelist;
	}
	
	@Transactional
	public List<AnimeList> findAnimeListByStatus(User user,int anime_id,String status) {
		List<AnimeList> animelist = alr.findAnimeListByStatus(user,anime_id,status);
		return animelist;
	}
	
	@Transactional
	public AnimeList addAnimeList(AnimeList animelist) {
 		
		return alr.save(animelist);
	}
	
}
