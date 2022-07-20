package com.revature.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.revature.models.AnimeList;
import com.revature.services.AnimeListService;

@SpringBootApplication
public class HashirasBackApplication {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(HashirasBackApplication.class, args);
	}
	
	@Component
	class AppStartupRunner implements ApplicationRunner {
	
		@Autowired
		private AnimeListService as;
		
	    @Override
	    public void run(ApplicationArguments args) throws Exception {
			List<AnimeList> al = as.getAnimeList();
			System.out.println(al.toString());

	    }
	}
}
