package com.revature.services;


import java.util.List;

import javax.persistence.Id;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.AnimeListNotFoundException;
import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;
import com.revature.repositories.AnimeListRepository;

@Service
public class AnimeListService {
	
	private AnimeListRepository alr;
	
	@Autowired
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
	public AnimeList findAnimeListByUserAndAnimeId(User user,int anime_id) {
		AnimeList animelist = alr.findAnimeListByUserAndAnimeId(user,anime_id);
		return animelist;
	}

	@Transactional
	public List<AnimeList> findAnimeListByAnimeId(int anime_id) {
		List<AnimeList> animelist = alr.findAnimeListByAnimeId(anime_id);
		return animelist;
	}
	
	@Transactional
	public List<AnimeList> findAnimeListByUser(User user) {
		List<AnimeList> animelist = alr.findAnimeListByUser(user);
		return animelist;
	}

	@Transactional
	public List<AnimeList> findAnimeListByUserAndAnimeIdAndStatus(User user,int anime_id,String status) {
		List<AnimeList> animelist = alr.findAnimeListByUserAndAnimeIdAndStatus(user,anime_id,status);
		return animelist;
	}	
	
	@Transactional
	public AnimeList addAnimeList(AnimeList animelist) {
 		
		return alr.save(animelist);
	}


	public List<AnimeList> findAnimeListByUserAndStatus(User user, ListStatus status) {
		List<AnimeList> animelist = alr.findAnimeListByUserAndStatus(user, status);
		return animelist;
	}	

	
	@Transactional
	public int updateStatusById(ListStatus status,int id) {
		  int success = alr.updateStatusById(status,id);
		return success;
	}	

	
	@Transactional
	public int updateRatingById(int user_rating,int id) {
 		  int success = alr.updateRatingById(user_rating,id);
		return success;
	}	
	
	@Transactional
	public void deleteAnimeListById(int id) throws AnimeListNotFoundException {
 		   alr.deleteById(id);

	}	

}
