package com.project.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//JPA naming 쿼리
	// select * from user where username = ? and password = ?
	User findByUsernameAndPassword(String username, String password);
	
	// native쿼리로 사용가능
//	@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//	User login(String username, String password);
	
	Optional<User> findByUsername(String username);

}
