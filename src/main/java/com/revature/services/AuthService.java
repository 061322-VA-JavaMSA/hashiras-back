package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {

	private UserRepository ur;

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public User login(String username, String password) {
		System.out.println(username);
		User principal = ur.findUserByUsername(username);
		System.out.println(principal);

 		// AuthLogic
		if(principal == null || password == null || !password.equals(principal.getPassword())){
			throw new AuthenticationException();
		}
		
		return principal;
	}
	
	public String generateToken(User principal) {
		// NOT BEST PRACTICE, JUST EXAMPLE PURPOSE
		String token = principal.getId() + ":" + principal.getUsername();
		return token;
	}
}
