package com.ssafy.blog.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.blog.dto.UserDto;
import com.ssafy.blog.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	private static final String baseDir = "C:" + File.separator + "Temp";
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDto login(Map<String, String> map) {
		return userMapper.login(map);
	}
	@Override
	public String findPw(Map<String, String> map) {
		return userMapper.findPw(map);
	}
	@Override
	public String findEmail(Map<String, String> map) {
		return userMapper.findEmail(map);
	}
	@Override
	public int delete(String uid) {
		return userMapper.delete(uid);
	}
	@Override
	public int join(UserDto dto) {
		return userMapper.join(dto);
	}
	@Override
	public int update(UserDto dto) {
		return userMapper.update(dto);
	}
	@Override
	public UserDto searchByEmail(String useremail) {
		return userMapper.selectByEmail(useremail);
	}
	@Override
	public int dupCheck(Map<String, String> map) {
		return userMapper.dupCheck(map);
	}
	@Override
	public int confirmValidation(String email) {
		return userMapper.confirmValidation(email);
	}
	@Override
	public String checkValid(String email) {
		return userMapper.checkValid(email);
	}
	@Override
	public UserDto updateFile(String email, MultipartFile files) {
		String fileName = files.getOriginalFilename();
		System.out.println(fileName);
		// 파일 확장자부분 떼오기
		String extension = fileName.split("\\.(?=[^\\.]+$)")[1];
		System.out.println(extension);

		// Random File Id
		UUID uuid = UUID.randomUUID();

		// 기존 파일명을 버리고 UUID로 새로 구성된 unique 파일명을 지정
		String savingFileName = uuid + "." + extension;

		// C:\Temp 경로에 물리 파일 저장
		try {
			files.transferTo(new File(baseDir + File.separator + savingFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("email", email);
		fileMap.put("image", fileName);
		fileMap.put("image_url", baseDir + "/" + savingFileName);
		
		// 성공적으로 파일 정보를 업데이트 한 경우 user 객체를 리턴
		if (userMapper.updateFile(fileMap) > 0) {
			return userMapper.detail(email);
		}
		
		return null;
	}
	
}
