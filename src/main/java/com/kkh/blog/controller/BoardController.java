package com.kkh.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kkh.blog.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
}
