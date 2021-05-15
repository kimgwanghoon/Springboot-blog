package com.kkh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.dto.ResponseDto;
import com.kkh.blog.model.Board;
import com.kkh.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {	
		boardService.writeService(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id) {	
		boardService.deleteService(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	
	}
}
