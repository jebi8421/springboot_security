package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.model.Board;
import com.project.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping(value="'','/'")
	public String index() {
		
		return "index";
	}
	
	@GetMapping(value="/board")
	public String board(Model model, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size) {
		
		if(page == null) {
			page = 0;
		}
		
		if(size == null) {
			size = 3;
		}
		
		PageRequest pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		
		Page<Board> boardList =	boardService.boardList(pageable);
		int pageNum = 0;
		if(boardList.getTotalElements()%size > 0) {
			pageNum = (int)boardList.getTotalElements()/size + 1;
		} else {
			pageNum = (int)boardList.getTotalElements()/size;
		}
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNum", pageNum);		
		return "index";
	}
	
	@GetMapping(value="/board/writeForm")
	public String write() {
		return "board/writeForm";
	}
	
	@GetMapping(value="/board/{id}")
	public String detail(@PathVariable int id, Model model) {
		
		boardService.updateView(id);
		Board board = boardService.detail(id);
		
		model.addAttribute("board", board);
		
		return "board/detail";
	}
	
	@GetMapping(value="/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		Board board = boardService.detail(id);
		
		model.addAttribute("board", board);
		
		return "board/updateForm";
	}
}
