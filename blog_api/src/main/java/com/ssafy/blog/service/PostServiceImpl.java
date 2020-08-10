package com.ssafy.blog.service;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.blog.dto.PostDto;
import com.ssafy.blog.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostMapper postMapper;
	
	@Override
	public List<PostDto> searchAll() throws Exception {
		return postMapper.selectAllPosts();
	}

	@Override
	public List<PostDto> searchByTitle(String title) throws Exception {
		System.out.println(URLDecoder.decode(title, "UTF-8"));
		return postMapper.selectByTitle(URLDecoder.decode(title, "UTF-8"));
	}

	@Override
	public List<PostDto> searchByUid(String userUid) throws Exception {
		System.out.println(URLDecoder.decode(userUid, "UTF-8"));
		return postMapper.selectByUid(URLDecoder.decode(userUid, "UTF-8"));
	}

	@Override
	public PostDto getPostDetail(String postId) {
		return postMapper.selectPostById(postId);
	}

	@Override
	public int bookmarkPost(Map<String, String> map) throws Exception {
		return postMapper.bookmarkPost(map);
	}

	@Override
	public int unBookmarkPost(Map<String, String> map) throws Exception {
		return postMapper.unBookmarkPost(map);
	}

	@Override
	public int isBookmarkedPost(Map<String, String> map) throws Exception {
		return postMapper.isBookmarkedPost(map);
	}

	@Override
	public int writePost(PostDto post) throws Exception {
		return postMapper.addPost(post);
	}

	@Override
	public int update(PostDto dto) throws Exception {
		return postMapper.update(dto);
	}

	@Override
	public int delete(String postId) throws Exception {
		return postMapper.delete(postId);
	}

}
