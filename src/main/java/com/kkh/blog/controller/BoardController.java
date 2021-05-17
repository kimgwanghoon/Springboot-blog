package com.kkh.blog.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.model.Board;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;
import com.kkh.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String index(Model model,@PageableDefault(size=4,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		model.addAttribute("list", boardService.list(pageable));
		return "index";
	}
	
	@GetMapping("/board/form")
	public String saveForm() {
		return "board/form";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/update";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		boardService.viewCount(id);
		model.addAttribute("detail",boardService.detail(id));
		return "board/detail";
	}
	
}
