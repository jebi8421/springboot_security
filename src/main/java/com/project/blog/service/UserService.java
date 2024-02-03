package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public int join(User user) {
		
		String orgPass = user.getPassword();
		String encPass = encoder.encode(orgPass);
		
		user.setPassword(encPass);
		user.setRole(RoleType.USER);
		
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.getMessage();
			return -1;
		}
		
	}
	
	@Transactional(readOnly = true) //select시 트랜젝션 시작, 서비스 종료시 트랜잭션 종료(정합성 유지)
	public User login(User user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
	}
	
	@Transactional
	public int update(int id, User user) {
		User orgUser = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 사용자정보가 없습니다.");
		});
		
		orgUser.setPassword(encoder.encode(user.getPassword()));
		orgUser.setEmail(user.getEmail());
		
		return 1;
	}
	
	@Transactional(readOnly = true)
	public User ExistUser(String username) {
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		
		return user;
	}
}
