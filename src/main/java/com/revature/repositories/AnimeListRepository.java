package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.AnimeList;
import com.revature.models.User;

public interface AnimeListRepository extends JpaRepository<AnimeList, Integer> {
 	User findAnimeListByUser(User user);

}
