package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(exposedHeaders="Authorization", origins="*")
public class AuthController {

	private AuthService as;

	@Autowired
	public AuthController(AuthService as) {
		super();
		this.as = as;
	}
	
	/*-
	 * Query params: localhost:8080/auth?username=[value]&password=[value]
	 * 
	 * Using URL form Encodded localhost:8080/auth
	 * 	- ?username=[value]&password=[value] in the request body
	 */
	@PostMapping
	public ResponseEntity<UserDTO> login(@RequestParam(name="username")String username, @RequestParam(name="password") String password){
 
		User principal = as.login(username, password);
		
		String token = as.generateToken(principal);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", token);		
		
		return new ResponseEntity<>(new UserDTO(principal), headers, HttpStatus.OK);
		
		
  	}
}
