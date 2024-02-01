package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomDetailUserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("사용자를 찾을수 없습니다.");
		});
		System.out.println("username : " + username);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		if(user != null) {
			return new PrincipalDetail(user);
		}
		
		return null;
	} 
	
}
