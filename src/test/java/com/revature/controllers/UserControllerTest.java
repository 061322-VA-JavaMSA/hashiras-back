package com.revature.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.HashirasBackApplication;
import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;

@SpringBootTest(classes=HashirasBackApplication.class)
public class UserControllerTest {
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
	public void getUserById() throws JsonProcessingException, Exception {
		User uservExpected = new User();
		uservExpected.setId(1);
		uservExpected.setUsername("kev");
		uservExpected.setPassword("pass");
		

		UserDTO expected = new UserDTO(uservExpected);
		
		when(us.getUserById(1)).thenReturn(uservExpected);
		
		mockMvc.perform(
				get("/users/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(om.writeValueAsString(expected)));
	}
		
 
	@Test
	public void createUser() throws JsonProcessingException, Exception {
		User u = new User();
		u.setUsername("username");
		u.setFname("fname");
		u.setLname("lname");
		u.setPassword("password");
		u.setEmail("email@email.com");
		
		when(us.addUser(u)).thenReturn(u);
		
    	mockMvc.perform(
				post("/users")
				 .content(om.writeValueAsString(u))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)
				)
    			.andExpect(status().isCreated());
	}

}
