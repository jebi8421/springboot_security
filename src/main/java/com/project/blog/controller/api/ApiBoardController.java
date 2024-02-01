package com.project.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.dto.ResponseDto;
import com.project.blog.model.Board;
import com.project.blog.model.User;
import com.project.blog.repository.BoardRepository;
import com.project.blog.service.BoardService;

@RestController
public class ApiBoardController {
	
	@Autowired
	private BoardService boardService;

	@PostMapping(value="/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		
		int result = boardService.save(board, principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	@PutMapping(value="/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {

		int result = boardService.update(id, board);
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
		
	}
	
	@DeleteMapping(value="/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id, @RequestBody User user, @AuthenticationPrincipal PrincipalDetail principal) {
		if(!user.getUsername().equals(principal.getUsername())) {
			return new ResponseDto<Integer>(HttpStatus.NOT_ACCEPTABLE, -1);
		}
		
		int result = boardService.delete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	/*
	@DeleteMapping(value="/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id, @RequestBody Map<String, String> usernameMap, @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println(usernameMap.get("username"));
		System.out.println(principal.getUsername());
		if(username != principal.getUsername()) {
			
		}
		
		return null;
	}
	*/
}
