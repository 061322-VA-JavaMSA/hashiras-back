package com.revature.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;

@Repository
public interface AnimeListRepository extends JpaRepository<AnimeList, Integer> {
	List<AnimeList>  findAnimeListByUser(User user);
	AnimeList findAnimeListByUserAndAnimeId(User user,int anime_id);
	List<AnimeList>  findAnimeListByAnimeId(int anime_id);
	List<AnimeList> findAnimeListByUserAndAnimeIdAndStatus(User user,int anime_id,String status);
	List<AnimeList> findAnimeListByUserAndStatus(User user, ListStatus status);
	List<AnimeList> findAnimeListByAnimeIdAndStatus(int anime_id,String status);
	
	@Transactional 
	@Modifying
	@Query("update AnimeList set status = ?1 where animeId = ?2 and user = ?3") int updateStatus(ListStatus status,int anime_id,User user);
	
	@Transactional 
	@Modifying
	@Query("update AnimeList set status = ?1 where id = ?2") int updateStatusById(ListStatus status,int id);
	
	@Transactional 
	@Modifying
	@Query("update AnimeList set user_rating = ?1 where id = ?2") int updateRatingById(int user_rating,int id);
}
