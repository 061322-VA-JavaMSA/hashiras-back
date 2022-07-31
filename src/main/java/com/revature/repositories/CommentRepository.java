package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.AnimeComments;
import com.revature.models.User;

@Repository
public interface CommentRepository extends JpaRepository<AnimeComments, Integer>{
	List<AnimeComments> findAnimeCommentsByAnimeId(int anime_id);
//	List<AnimeComments> findAnimeCommentsByAnimeIdAndUser(int anime_id, User user);
}
