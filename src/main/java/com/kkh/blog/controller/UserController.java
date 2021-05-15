package com.kkh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

//주소가 "/" 이거나 /auth/ 하단의 경로는 인증이 안된 사용자도 사용할 수 있도록 허용, 그 외 경로는 인증된 사용자만 접근하도록 
@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/loginFail")
	public String loginFail() {
		return "user/loginFail";
	}
	
}
