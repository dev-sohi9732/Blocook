package com.ssafy.blog.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.blog.dto.PostDto;

@Mapper
public interface PostMapper {
	public List<PostDto> selectAllPosts();
	
	public List<PostDto> selectByTitle(String title);
	
	public List<PostDto> selectByUid(String userUid);
	
	public List<PostDto> selectByNickname(String nickname);
	
	public PostDto selectPostById(String id);
	
	public int bookmarkPost(Map<String, String> map) throws Exception;
	
	public int unBookmarkPost(Map<String, String> map) throws Exception;
	
	public int isBookmarkedPost(Map<String, String> map) throws Exception;
	
	public int addPost(PostDto dto);
	
	public int update(PostDto dto);
	
	public int delete(String id);

	public int addViewCount(String id);
}
