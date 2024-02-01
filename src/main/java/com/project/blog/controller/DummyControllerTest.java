package com.project.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.model.RoleType;
import com.project.blog.model.User;
import com.project.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/dummy/users")
	public List<User> list() {
		
		return userRepository.findAll();
	}
	
	// 페이지당 2건
	public List<User> pageList(@PageableDefault(size = 2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		List<User> list = users.getContent();
		
		return list;
	}

	@GetMapping(value="/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// 없는 데이터일 경우 null이 되기때문에 optional로 오브젝트 객체를 감싸서 가져오니 null인지 판단
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. " + id);
//			}
//		});
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 유저는 존재하지 않습니다. " + id);
		});
		
		return user;
	}
	
	@PostMapping(value="/dummy/join")
	public String dummy(@RequestBody User user) {
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		return "더미 데이터 생성을 성공하였습니다.";
	}
	
	@Transactional
	@PutMapping(value="/dummy/user/{id}")
	public User update(@PathVariable int id, @RequestBody User requestUser) {
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 유저가 존재하지 않습니다." + id);
		});
		
		user.setUsername(requestUser.getUsername());
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		return user;
	}
	
	@DeleteMapping(value="/dummy/user/{id}")
	public String delete(@PathVariable int id) {
//		User user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당 유저가 존재하지 않습니다." + id);
//		});
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "해당 유저가 존재하지 않습니다." + id;
		}
		
		return "삭제되었습니다.";
	}
}
