package com.project.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.User;
import com.project.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ApiUserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/auth/joinProc")
	public ResponseDto<Integer> joinProc(@RequestBody User user) {
		
		int result = userService.join(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
				
	}
	
	@PostMapping(value="/auth/loginProc")
	public ResponseDto<Integer> login(@RequestBody User user) {
		return null;
	}
	
	@PostMapping(value="/api/user/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody User user,
										@AuthenticationPrincipal PrincipalDetail principal,
										HttpSession session) {
		int result = userService.update(id, user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	
//	@PostMapping(value="/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		
//		User principal = userService.login(user);
//		int result = 0;
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//			result = 1;
//		} else {
//			result = -1;
//		}
//		
//		System.out.println("principal : " + principal);
//		
//		return new ResponseDto<Integer>(HttpStatus.OK, result);
//	}
//	
}
