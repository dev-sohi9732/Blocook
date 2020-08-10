package com.ssafy.blog.controller;

import java.util.List;
import java.util.Map;

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

import com.ssafy.blog.dto.PostDto;
import com.ssafy.blog.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/posts")
public class PostController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PostService postService;
	
	@ApiOperation(value = "모든 포스트들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/all")
	public ResponseEntity<List<PostDto>> searchAll() throws Exception {
		List<PostDto> posts = postService.searchAll();

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트 제목을 받아 해당 제목과 일치하는 모든 포스트들을 반환한다.", response = List.class)
	@GetMapping(value = "/search/title/{title}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String title) throws Exception {
		
		List<PostDto> result = postService.searchByTitle(title);
		
		if (result == null) {
			return new ResponseEntity<List<PostDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 아이디(UID)로 받아 해당 사용자가 등록한 모든 레시피들을 반환한다.", response = List.class)
	@PostMapping(value = "/search/uid")
	public ResponseEntity<List<PostDto>> searchByUid(@RequestBody String uid) throws Exception {
		List<PostDto> result = postService.searchByUid(uid);
		
		if (result == null) {
			return new ResponseEntity<List<PostDto>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피 ID에 해당하는 레시피 정보를 반환한다.", response = PostDto.class)
	@GetMapping(value = "/{postId}")
	public ResponseEntity<PostDto> getPostDetail(@PathVariable String postId) throws Exception {
		
		PostDto result = postService.getPostDetail(postId);
		
		if (result == null) {
			return new ResponseEntity<PostDto>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<PostDto>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트 즐겨찾기 등록", response = String.class)
	@PostMapping(value = "/bookmark")
	public ResponseEntity<String> bookmarkPost(@RequestBody Map<String,String> params) throws Exception {
		System.out.println(params.get("uid"));
		int result = 0;
		try {
			result = postService.bookmarkPost(params); // uid와 postId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 중복 키 등 제약조건 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 포스트 즐겨찾기 해제", response = String.class)
	@DeleteMapping(value = "/unbookmark/{uid}/{postId}")
	public ResponseEntity<String> unBookmarkRecipe(@PathVariable Map<String, String> params) throws Exception {

		int result = 0;
		try {
			result = postService.unBookmarkPost(params); // uid와 postId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "좋아요 누른 포스트인지 판단", response = String.class)
	@PostMapping(value = "/isbookmarked")
	public ResponseEntity<String> isBookmarkedPost(@RequestBody Map<String,String> params) throws Exception {
		
		int result = 0;
		try {
			result = postService.isBookmarkedPost(params); // uid와 recipeId가 들어 있는 map
		} catch (Exception e) {
			System.out.println(e.getMessage()); // 중복 키 등 제약조건 예외 처리
		}
		
		if (result == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트 등록하기", response = Integer.class)
	@PostMapping(value = "/write")
	public ResponseEntity<String> writePost(@RequestBody PostDto dto) throws Exception {
		
		if (postService.writePost(dto) == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		System.out.println("success");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트 수정하기", response = String.class)
	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody PostDto dto) throws Exception {
		int total = postService.update(dto);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "포스트 아이디를 받아 삭제", response = String.class)
	@DeleteMapping(value = "/delete/{postid}")
	public ResponseEntity<String> delete(@PathVariable String postid) throws Exception {
		int total = postService.delete(postid);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}


}
