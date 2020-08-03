package com.ssafy.blog.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.blog.dto.UserDto;
import com.ssafy.blog.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/account")
public class UserController {

	private static final String baseDir = "C:" + File.separator + "Temp";

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender mailSender;

	@ApiOperation(value = "유저 아이디와 패스워드로 로그인을 시도하고, 성공시 세션에 userDto로 정보를 저장한다.", response = UserDto.class)
	@PostMapping(value = "/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto user) throws Exception {
		Map<String, String> loginMap = new HashMap<String, String>();
		loginMap.put("email", user.getEmail());
		loginMap.put("password", user.getPassword());
		UserDto userDto = userService.login(loginMap);

		// 회원 가입 인증 여부 확인은 프론트단에서 처리
//		boolean isValid = userService.checkValid(user.getEmail()).charAt(0) == 'y' ? true : false; // 회원가입 인증 여부

		if (userDto == null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_FOUND);
		}
		System.out.println(userDto);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자의 정보를 받아 회원 가입한다.", response = String.class)
	@PostMapping(value = "/signup")
	public ResponseEntity<String> join(@RequestBody UserDto dto) throws Exception {
		System.out.println(dto);
		// 아이디 및 이메일 중복 확인
		Map<String, String> dupCheckMap = new HashMap<String, String>();
		dupCheckMap.put("nickname", dto.getNickname());
		dupCheckMap.put("email", dto.getEmail());
		System.out.println(dto);
		if (userService.dupCheck(dupCheckMap) > 0) { // 중복되는 경우 실패
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		if (userService.join(dto) == 0) { // 정상적으로 삽입하지 못한 경우 실패
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		System.out.println("success");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자의 nickname과 email 정보가 일치할 때만 비밀번호를 반환한다.", response = String.class)
	@GetMapping(value = "/findpw/{findnickname}/{findemail}")
	public ResponseEntity<String> findPw(@PathVariable String findnickname, @PathVariable String findemail) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("nickname", findnickname);
		map.put("email", findemail);
		String upassword = userService.findPw(map);

		if (upassword == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<String>(upassword, HttpStatus.OK);

	}
	
	@ApiOperation(value = "사용자의 nickname과 password 정보가 일치할 때만 이메일을 반환한다.", response = String.class)
	@GetMapping(value = "/findemail/{findnickname}/{findpassword}")
	public ResponseEntity<String> findEmail(@PathVariable String findnickname, @PathVariable String findpassword) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("nickname", findnickname);
		map.put("password", findpassword);
		String upassword = userService.findEmail(map);

		if (upassword == null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<String>(upassword, HttpStatus.OK);

	}

	@ApiOperation(value = " 사용자 이메일로 사용자 정보를 가져온다.", response = String.class)
	@GetMapping(value = "/userinfo/{useremail}")
	public ResponseEntity<UserDto> update(@PathVariable String useremail) throws Exception {
		UserDto user = userService.searchByEmail(useremail);

		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 닉네임 중복을 확인한다.", response = String.class)
	@GetMapping(value = "/update/{nickname}")
	public ResponseEntity<String> checkNickname(@PathVariable String nickname) throws Exception {
		// 아이디 및 이메일 중복 확인
		Map<String, String> dupCheckMap = new HashMap<String, String>();
		dupCheckMap.put("nickname", nickname);
		dupCheckMap.put("email", "");
		System.out.println("=======nickname: "+nickname);
		if (userService.dupCheck(dupCheckMap) > 0) { // 중복되는 경우 실패
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = " 사용자 정보를 수정한다.", response = String.class)
	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody UserDto dto) throws Exception {
		int total = userService.update(dto);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "사용자의 아이디값을 받아 DB에 일치하는 회원 정보를 삭제한다.", response = String.class)
	@DeleteMapping(value = "/delete/{uid}")
	public ResponseEntity<String> delete(@PathVariable String uid) throws Exception {
		int total = userService.delete(uid);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@ApiOperation(value = "회원가입 인증 이메일을 전송합니다.", response = String.class)
	@GetMapping(value = "/validation/send/{email}")
	public ResponseEntity<String> sendEmailAction(@PathVariable String email) throws Exception {
		System.out.println(email);
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setSubject("[Chan's Devlog] 회원가입 인증 이메일입니다.");
			messageHelper.setText(new StringBuilder().append("아래 링크에 접속하여 인증을 진행해주세요!").append("\n")
					.append("http://localhost:9999/account/validation/confirm/").append(email).toString());
			messageHelper.setTo(email);
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
			mailSender.send(msg);
		} catch (MessagingException e) {
			System.out.println("MessagingException");
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "회원가입 인증을 진행합니다.", response = String.class)
	@GetMapping(value = "/validation/confirm/{email}")
	public void confirmValidation(@PathVariable String email, HttpServletResponse httpServletResponse)
			throws Exception {
		int total = userService.confirmValidation(email);

		if (total == 0) {
			httpServletResponse.sendRedirect("http://localhost:8080/user/validation/fail");
			return;
		}

		httpServletResponse.sendRedirect("http://localhost:8080/user/validation/success");
	}

	@ApiOperation(value = "해당 이메일 계정에 이미지를 업로드합니다.", response = String.class)
	@PostMapping(value = "/upload/{email}")
	public ResponseEntity<UserDto> fileUpload(@PathVariable String email, @RequestPart MultipartFile files) {
		
		UserDto userDto = userService.updateFile(email, files);

		if (userDto == null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_FOUND);
		}
		System.out.println(userDto);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/error/test")
	public ResponseEntity<String> errorTest() {
		try {
			throw new NullPointerException();
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
