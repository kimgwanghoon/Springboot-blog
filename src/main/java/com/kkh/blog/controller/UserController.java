package com.kkh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@Controller
public class UserController {
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
