package com.revature.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.HashirasBackApplication;
import com.revature.dto.AnimeListDTO;
import com.revature.models.AnimeList;
import com.revature.models.ListStatus;
import com.revature.models.User;
import com.revature.services.AnimeListService;
import com.revature.services.UserService;

import static org.mockito.Mockito.when;

@SpringBootTest(classes=HashirasBackApplication.class)
public class AnimeListControllerTest {
	@MockBean
	private AnimeListService as;
	@MockBean
	private UserService us;	
	@Autowired
	private WebApplicationContext context;
	
	private ObjectMapper om = new ObjectMapper();
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void getById() throws JsonProcessingException, Exception {
    	User uservExpected = new User();
    	uservExpected.setId(21);
    	uservExpected.setUsername("charles");;
    	uservExpected.setEmail("charles@charles.com");
    	uservExpected.setPassword("12345");
    	uservExpected.setFname("charles");
    	uservExpected.setLname("albert");
     	AnimeList animeListExpected  = new AnimeList();
     	animeListExpected.setId(21);
     	animeListExpected.setAnimeId(20);
     	animeListExpected.setUser(uservExpected);
    	animeListExpected.setStatus(ListStatus.CURRENTLY);
     	AnimeListDTO expected = new AnimeListDTO(animeListExpected);
    	
    	when(as.getAnimeListById(21)).thenReturn(animeListExpected);
    				
		mockMvc.perform(
				get("/anime/21"))
			.andExpect(status().isOk())
			.andExpect(content().json(om.writeValueAsString(expected)));
//    	System.out.println( mockMvc.perform(
//				get("/anime/21"))
//    			.andExpect(status().isOk())
//    			.andReturn().getResponse().getContentAsString());
    	
	}
	
	
	@Test
	public void ByUserAndAnimeId() throws JsonProcessingException, Exception {
    	User uservExpected = new User();
    	uservExpected.setId(100);
    	uservExpected.setUsername("charles");;
    	uservExpected.setEmail("charles@charles.com");
    	uservExpected.setPassword("12345");
    	uservExpected.setFname("charles");
    	uservExpected.setLname("albert");
     	AnimeList animeListExpected  = new AnimeList();
     	animeListExpected.setId(100);
     	animeListExpected.setAnimeId(100);
     	animeListExpected.setUser(uservExpected);
     	animeListExpected.setUser_rating(10);
    	animeListExpected.setStatus(ListStatus.CURRENTLY);
     	AnimeListDTO expected = new AnimeListDTO(animeListExpected);
     	when(us.getUserById(100)).thenReturn(uservExpected);
    	when(as.findAnimeListByUserAndAnimeId(uservExpected,100)).thenReturn(animeListExpected);
 
		mockMvc.perform(
				get("/anime/user/100/anime/100"))
			.andExpect(status().isOk())
			.andExpect(content().json(om.writeValueAsString(expected)));
	}
	
	
	@Test
	public void updateStatusById() throws JsonProcessingException, Exception {
    	mockMvc.perform(
				put("/anime/100/status")
				.param("status", "CURRENTLY"))
    			.andExpect(status().isAccepted());
	}
	
	
	@Test
	public void updateRatingById() throws JsonProcessingException, Exception {
    	mockMvc.perform(
				put("/anime/100/rate")
				.param("user_rating", "5"))
    			.andExpect(status().isAccepted());
	}
	
	@Test
	public void deleteById() throws JsonProcessingException, Exception {
    	mockMvc.perform(
    			delete("/anime/100")
				.param("user_rating", "5"))
    			.andExpect(status().isOk());
	}
}
