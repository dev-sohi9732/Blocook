package com.ssafy.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.blog.dto.CommentDto;
import com.ssafy.blog.dto.PostDto;

@Mapper
public interface CommentMapper {

	public List<CommentDto> selectByPostId(String postId);
	
	public List<CommentDto> selectByUid(String userUid);
	
	public int addComment(CommentDto dto);
	
	public int update(CommentDto dto);
	
	public int delete(String id);
	
}
