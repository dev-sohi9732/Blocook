package com.ssafy.blog.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.blog.dto.UserDto;

public interface UserService {
	public UserDto login(Map<String, String> map);
	
	public int delete(String uid);
	
	public String findPw(Map<String, String> map);
	
	public String findEmail(Map<String, String> map);
	
	public int join(UserDto dto);
	
	public int update(UserDto dto);
	
	public UserDto searchByEmail(String useremail);
	
	public int dupCheck(Map<String, String> map);
	
	public int confirmValidation(String email);
	
	public String checkValid(String email);
	
	public UserDto updateFile(String email, MultipartFile files);
}
