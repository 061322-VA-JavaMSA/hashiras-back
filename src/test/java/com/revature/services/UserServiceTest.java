/**
 * 
 */
package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.HashirasBackApplication;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

/**
 * @author Dylan
 *
 */
@SpringBootTest(classes=HashirasBackApplication.class)
public class UserServiceTest {

	
	@MockBean
	private UserRepository ur;
	
	@Autowired
	private UserService us;	
//	@Autowired
//	private WebApplicationContext context;
//	
//	private ObjectMapper om = new ObjectMapper();
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	public void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
	@Test
	public void getUsers() throws JsonProcessingException, Exception {
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("charles");;
    	u1.setEmail("charles@charles.com");
    	u1.setPassword("12345");
    	u1.setFname("charles");
    	u1.setLname("albert");
    	
    	List<User> users = new ArrayList<>();
    	users.add(u1);
    	
    	Mockito.when(ur.findAll()).thenReturn(users);
    	
    	List<User> usersActual = us.getUsers();
    	
    	assertEquals(users, usersActual);
    	
	}

	@Test
	public void getUserByIdExists() throws UserNotFoundException {
		User udaoExpected = new User();
		udaoExpected.setId(1);
		udaoExpected.setUsername("charles");;
		udaoExpected.setEmail("charles@charles.com");
		udaoExpected.setPassword("12345");
		udaoExpected.setFname("charles");
		udaoExpected.setLname("albert");
		
		User uservExpected = new User();
		uservExpected.setId(1);
		uservExpected.setUsername("charles");
		uservExpected.setEmail("charles@charles.com");
		uservExpected.setPassword("12345");
		uservExpected.setFname("charles");
		uservExpected.setLname("albert");
		
		
		Mockito.when(ur.findById(1)).thenReturn(Optional.of(udaoExpected));
		
		User uservActual = us.getUserById(1);
		
		assertEquals(uservExpected, uservActual);
	}
	
	@Test
	public void getUserByIdDoesNotExist() throws UserNotFoundException {
		/*-
		 * Mocking allows us to "mock" dependencies:
		 * 		- in this case us will call .getUserById() from mockUserDao instead of UserHibernate
		 * 		- We can control what mockUserDao will return, in this case it will return null for id = 3
		 */
		Mockito.when(ur.findById(3)).thenReturn(Optional.empty());
		
		// us calls mockUserDao.getUserById(1); instead of calling UserHibernate's implementation - ud.getUserById(1)
		assertThrows(UserNotFoundException.class, () -> us.getUserById(3));
	}
	
}
