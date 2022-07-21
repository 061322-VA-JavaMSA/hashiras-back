package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	 
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}

	public List<User> getUsers(){
		return ur.findAll();
	}
	
	@Transactional
	public User getUserById(int id) throws UserNotFoundException {
 		User user = ur.findById(id).orElseThrow(() -> new UserNotFoundException());
		return user;
	}
	
	@Transactional
	public User addUser(User user) {
		return ur.save(user);
	}
	
	@Transactional
	public int updateInfo(String fname,String lname,String email, int id) {
		return updateInfo( fname, lname, email,  id);
	}
	
	@Transactional
	public int updatePassword(String password, int id) {
		return updatePassword( password,  id);
	}
}
