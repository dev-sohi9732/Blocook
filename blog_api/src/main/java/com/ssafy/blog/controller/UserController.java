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

	@ApiOperation(value = "�쑀�� �븘�씠�뵒�� �뙣�뒪�썙�뱶濡� 濡쒓렇�씤�쓣 �떆�룄�븯怨�, �꽦怨듭떆 �꽭�뀡�뿉 userDto濡� �젙蹂대�� ���옣�븳�떎.", response = UserDto.class)
	@PostMapping(value = "/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto user) throws Exception {
		Map<String, String> loginMap = new HashMap<String, String>();
		loginMap.put("email", user.getEmail());
		loginMap.put("password", user.getPassword());
		UserDto userDto = userService.login(loginMap);

		// �쉶�썝 媛��엯 �씤利� �뿬遺� �솗�씤�� �봽濡좏듃�떒�뿉�꽌 泥섎━
//		boolean isValid = userService.checkValid(user.getEmail()).charAt(0) == 'y' ? true : false; // �쉶�썝媛��엯 �씤利� �뿬遺�

		if (userDto == null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_FOUND);
		}
		System.out.println(userDto);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "�궗�슜�옄�쓽 �젙蹂대�� 諛쏆븘 �쉶�썝 媛��엯�븳�떎.", response = String.class)
	@PostMapping(value = "/signup")
	public ResponseEntity<String> join(@RequestBody UserDto dto) throws Exception {
		System.out.println(dto);
		// �븘�씠�뵒 諛� �씠硫붿씪 以묐났 �솗�씤
		Map<String, String> dupCheckMap = new HashMap<String, String>();
		dupCheckMap.put("nickname", dto.getNickname());
		dupCheckMap.put("email", dto.getEmail());
		System.out.println(dto);
		if (userService.dupCheck(dupCheckMap) > 0) { // 以묐났�릺�뒗 寃쎌슦 �떎�뙣
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		if (userService.join(dto) == 0) { // �젙�긽�쟻�쑝濡� �궫�엯�븯吏� 紐삵븳 寃쎌슦 �떎�뙣
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		System.out.println("success");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "�궗�슜�옄�쓽 nickname怨� email �젙蹂닿� �씪移섑븷 �븣留� 鍮꾨�踰덊샇瑜� 諛섑솚�븳�떎.", response = String.class)
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
	
	@ApiOperation(value = "�궗�슜�옄�쓽 nickname怨� password �젙蹂닿� �씪移섑븷 �븣留� �씠硫붿씪�쓣 諛섑솚�븳�떎.", response = String.class)
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

	@ApiOperation(value = " �궗�슜�옄 �씠硫붿씪濡� �궗�슜�옄 �젙蹂대�� 媛��졇�삩�떎.", response = String.class)
	@GetMapping(value = "/userinfo/{useremail}")
	public ResponseEntity<UserDto> update(@PathVariable String useremail) throws Exception {
		UserDto user = userService.searchByEmail(useremail);

		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = " �땳�꽕�엫 以묐났�쓣 �솗�씤�븳�떎.", response = String.class)
	@GetMapping(value = "/update/{nickname}")
	public ResponseEntity<String> checkNickname(@PathVariable String nickname) throws Exception {
		// �븘�씠�뵒 諛� �씠硫붿씪 以묐났 �솗�씤
		Map<String, String> dupCheckMap = new HashMap<String, String>();
		dupCheckMap.put("nickname", nickname);
		dupCheckMap.put("email", "");
		System.out.println("=======nickname: "+nickname);
		if (userService.dupCheck(dupCheckMap) > 0) { // 以묐났�릺�뒗 寃쎌슦 �떎�뙣
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = " �궗�슜�옄 �젙蹂대�� �닔�젙�븳�떎.", response = String.class)
	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody UserDto dto) throws Exception {
		int total = userService.update(dto);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "�궗�슜�옄�쓽 �븘�씠�뵒媛믪쓣 諛쏆븘 DB�뿉 �씪移섑븯�뒗 �쉶�썝 �젙蹂대�� �궘�젣�븳�떎.", response = String.class)
	@DeleteMapping(value = "/delete/{uid}")
	public ResponseEntity<String> delete(@PathVariable String uid) throws Exception {
		int total = userService.delete(uid);

		if (total == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}

	@ApiOperation(value = "�쉶�썝媛��엯 �씤利� �씠硫붿씪�쓣 �쟾�넚�빀�땲�떎.", response = String.class)
	@GetMapping(value = "/validation/send/{email}")
	public ResponseEntity<String> sendEmailAction(@PathVariable String email) throws Exception {
		System.out.println(email);
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setSubject("[Chan's Devlog] �쉶�썝媛��엯 �씤利� �씠硫붿씪�엯�땲�떎.");
			messageHelper.setText(new StringBuilder().append("�븘�옒 留곹겕�뿉 �젒�냽�븯�뿬 �씤利앹쓣 吏꾪뻾�빐二쇱꽭�슂!").append("\n")
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

	@ApiOperation(value = "�쉶�썝媛��엯 �씤利앹쓣 吏꾪뻾�빀�땲�떎.", response = String.class)
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

	@ApiOperation(value = "�빐�떦 �씠硫붿씪 怨꾩젙�뿉 �씠誘몄�瑜� �뾽濡쒕뱶�빀�땲�떎.", response = String.class)
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
