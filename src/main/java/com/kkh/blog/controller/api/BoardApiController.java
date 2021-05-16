package com.kkh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.dto.ReplyRequestDto;
import com.kkh.blog.dto.ResponseDto;
import com.kkh.blog.model.Board;
import com.kkh.blog.model.Reply;
import com.kkh.blog.repository.ReplyRepository;
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
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@RequestBody Board board,@PathVariable int id){
		boardService.updateService(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id) {	
		boardService.deleteService(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> saveReply(@RequestBody ReplyRequestDto replyRequestDto) {	
		boardService.writeReply(replyRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> deleteReply(@PathVariable int replyId) {	
		boardService.deleteReply(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	
	}
	
	/*	컨트롤러에서 dto를 만들지않고 데이터를 받는 방식 : 댓글저장
	 * @PostMapping("/api/board/{boardId}/reply") public ResponseDto<Integer>
	 * saveReply(@RequestBody Reply reply,@PathVariable int
	 * boardId,@AuthenticationPrincipal PrincipalDetail principal) {
	 * boardService.writeReply(boardId,reply,principal.getUser()); return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
}
