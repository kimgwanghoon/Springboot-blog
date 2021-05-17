package com.kkh.blog.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.dto.BoardListDto;
import com.kkh.blog.dto.ReplyRequestDto;
import com.kkh.blog.dto.ResponseDto;
import com.kkh.blog.model.Board;
import com.kkh.blog.model.Reply;
import com.kkh.blog.repository.ReplyRepository;
import com.kkh.blog.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.Data;

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
	
	//글목록 api 샘플 노출하고싶은 board데이터만 전달하도록 가공, 한번에 4건씩 데이터 전달
	@GetMapping("/auth/board/list")
	public  Result boardList(Model model,@PageableDefault(size=4,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Board> list= boardService.list(pageable);
		List<BoardListDto>collect = list.stream()
				.map(m -> new BoardListDto(m.getId(),m.getTitle(),m.getContent(),m.getCount()))
				.collect(Collectors.toList());
		
		return new Result(collect);
	}
	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
