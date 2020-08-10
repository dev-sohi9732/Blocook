package com.ssafy.blog.service;

import java.util.List;

import com.ssafy.blog.dto.CommentDto;
import com.ssafy.blog.dto.PostDto;

public interface CommentService {

	public List<CommentDto> searchByPostId(String postId) throws Exception;
	
	public List<CommentDto> searchByUid(String userUid) throws Exception;
	
	public int writeComment(CommentDto comment) throws Exception;
	
	public int update(CommentDto dto) throws Exception;
	
	public int delete(String id) throws Exception;
}
