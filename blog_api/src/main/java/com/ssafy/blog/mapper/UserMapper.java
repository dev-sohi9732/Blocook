package com.ssafy.blog.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.blog.dto.UserDto;

@Mapper
public interface UserMapper {
	
	public UserDto login(Map<String, String> map);
	public int delete(String uid);
	public String findPw(Map<String, String> map);
	public String findEmail(Map<String, String> map);
	public int join(UserDto dto);
	public int update(UserDto dto);
	public UserDto selectByEmail(String useremail);
	public int dupCheck(Map<String, String> map);
	public int confirmValidation(String email);
	public String checkValid(String email);
	public UserDto detail(String email);
	public int updateFile(Map<String, String> map);
}
