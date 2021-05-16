package com.kkh.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.dto.ResponseDto;
import com.kkh.blog.model.User;
import com.kkh.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/signup")
	public ResponseDto<Integer> save(@RequestBody User user) {	
		userService.save(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	//자바오브젝트를 Jacson이 Json형식으로 변환해서리턴해준다.
	}
	
	@PutMapping("/user/update")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		//세션등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
