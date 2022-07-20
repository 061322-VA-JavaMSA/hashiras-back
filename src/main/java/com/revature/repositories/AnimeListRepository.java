package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.AnimeList;
import com.revature.models.User;

@Repository
public interface AnimeListRepository extends JpaRepository<AnimeList, Integer> {
	List<AnimeList>  findAnimeListByUser(User user);
 	List<AnimeList> findAnimeListByUserAnimeId(User user,int anime_id);
 	List<AnimeList> findAnimeListByStatus(User user,int anime_id,String status);
}
