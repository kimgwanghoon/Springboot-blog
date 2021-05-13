package com.kkh.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.dto.ResponseDto;
import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {	
		user.setRole(RoleType.user);	
		
		userService.save(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	//자바오브젝트를 Jacson이 Json형식으로 변환해서리턴해준다.
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){	
		User okUser = userService.login(user);
		if(okUser !=null) {
			session.setAttribute("okUser", okUser);	//세션생성
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}
