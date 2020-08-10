package com.ssafy.blog.service;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.blog.dto.CommentDto;
import com.ssafy.blog.dto.PostDto;
import com.ssafy.blog.mapper.CommentMapper;
import com.ssafy.blog.mapper.PostMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public List<CommentDto> searchByPostId(String postId) throws Exception {
		System.out.println(URLDecoder.decode(postId, "UTF-8"));
		return commentMapper.selectByPostId(URLDecoder.decode(postId, "UTF-8"));
	}

	@Override
	public List<CommentDto> searchByUid(String userUid) throws Exception {
		System.out.println(URLDecoder.decode(userUid, "UTF-8"));
		return commentMapper.selectByUid(URLDecoder.decode(userUid, "UTF-8"));
	}

	@Override
	public int writeComment(CommentDto comment) throws Exception {
		return commentMapper.addComment(comment);
	}

	@Override
	public int update(CommentDto dto) throws Exception {
		return commentMapper.update(dto);
	}

	@Override
	public int delete(String id) throws Exception {
		return commentMapper.delete(id);
	}

}
