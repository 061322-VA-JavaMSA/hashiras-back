package com.revature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;
import com.revature.services.AnimeListService;
import com.revature.services.UserService;

@SpringBootApplication
public class HashirasBackApplication {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(HashirasBackApplication.class, args);
	}
	

}
//@Component
//class AppStartupRunner implements ApplicationRunner {
//
//	@Autowired
//	private AnimeListService as;
//	private UserService us;
	
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//		List<AnimeList> al = as.getAnimeList();
//		System.out.println(al.toString());
//    	User user = new User();
//  	
//    	user.setId(6);
//    	user.setFname("g");;
//    	AnimeList animeList = new AnimeList();
//    	animeList.setAnimeId(20);
//    	animeList.setUser(user);
//    	animeList.setStatus(ListStatus.VIEWED);
//    	as.addAnimeList(animeList);
//    	
//    	List<User> users = us.getUsers();
//    	System.out.println(users.toString());
    	
//    }
//}