package com.project.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.model.Board;
import com.project.blog.repository.BoardRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping(value="/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		
		return boardRepository.findById(id).get();
	}
	
}
