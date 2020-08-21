package com.ssafy.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.blog.dto.CommentDto;
import com.ssafy.blog.dto.PostDto;
import com.ssafy.blog.service.CommentService;
import com.ssafy.blog.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "포스트 아이디로 댓글들 검색", response = List.class)
	@GetMapping(value = "/search/postid/{postId}")
	public ResponseEntity<List<CommentDto>> searchByPostId(@PathVariable String postId) throws Exception {
		List<CommentDto> result = commentService.searchByPostId(postId);
		
		if (result == null) {
			return new ResponseEntity<List<CommentDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 아이디(UID)로 받아 해당 사용자가 등록한 모든 댓글들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/uid/{uid}")
	public ResponseEntity<List<CommentDto>> searchByUid(@PathVariable String uid) throws Exception {
		List<CommentDto> result = commentService.searchByUid(uid);
		
		if (result == null) {
			return new ResponseEntity<List<CommentDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 작성", response = Integer.class)
	@PostMapping(value = "/write")
	public ResponseEntity<String> writePost(@RequestBody CommentDto dto) throws Exception {
		
		if (commentService.writeComment(dto) == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		System.out.println("success");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 수정하기", response = String.class)
	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody CommentDto dto) throws Exception {
		int total = commentService.update(dto);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 아이디를 받아 삭제", response = String.class)
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
		int total = commentService.delete(id);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}
}
