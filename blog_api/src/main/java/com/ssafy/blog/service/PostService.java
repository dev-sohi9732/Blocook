package com.ssafy.blog.service;

import java.util.List;
import java.util.Map;

import com.ssafy.blog.dto.PostDto;

public interface PostService {
	public List<PostDto> searchAll() throws Exception;
	
	public List<PostDto> searchByTitle(String title) throws Exception;
	
	public List<PostDto> searchByUid(String userUid) throws Exception;
	
	public List<PostDto> searchByNickname(String nickname) throws Exception;
	
	public PostDto getPostDetail(String id);
	
	public int bookmarkPost(Map<String, String> map) throws Exception;
	
	public int unBookmarkPost(Map<String, String> map) throws Exception;
	
	public int isBookmarkedPost(Map<String, String> map) throws Exception;
	
	public int writePost(PostDto post) throws Exception;
	
	public int update(PostDto dto) throws Exception;
	
	public int delete(String id) throws Exception;
}
