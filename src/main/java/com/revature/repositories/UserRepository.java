

package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import com.revature.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUsername(String username);
	@Transactional 
	@Modifying
	@Query("update User set fname = ?1, lname = ?2, email = ?3 where id = ?4") int updateInfo(String fname,String lname,String email, int id);
	@Transactional 
	@Modifying
	@Query("update User set password = ?1 where id = ?2") int updatePassword(String password, int id);
}
