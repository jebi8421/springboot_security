package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.blog.model.Board;
import com.project.blog.model.User;
import com.project.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		Page<Board> boardList = boardRepository.findAll(pageable);
		
		return boardList;
	}
	
	@Transactional
	public int save(Board board, User user) {
		try {
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
			return 1;
		} catch (Exception e) {
			e.getMessage();
			return -1;
		}
	}
	
	@Transactional(readOnly = true)
	public Board detail(int id) {
		Board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 게시물은 존재하지 않습니다.");
		});
		
		return board;
	}
	
	@Transactional
	public int delete(int id) {
		try {
			boardRepository.deleteById(id);
			return 1;
		} catch(Exception e) {
			e.getMessage();
			return -1;
		}
	}
	
	@Transactional
	public int update(int id, Board board) {
		
		Board orgBoard = boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 게시물은 존재하지 않습니다.");
		});
		
		orgBoard.setTitle(board.getTitle());
		orgBoard.setContent(board.getContent());
		
		return 1;
	}
	
	@Transactional
	public void updateView(@Param("id") int id) {
		boardRepository.updateView(id);
	}
}
