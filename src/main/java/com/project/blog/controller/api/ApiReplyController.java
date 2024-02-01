package com.project.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.dto.ResponseDto;

@RestController
public class ApiReplyController {

	@PostMapping(value="/api/reply")
	public ResponseDto<Integer> reply(@RequestBody String content) {
		
		int result = 1;
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
