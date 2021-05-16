package com.kkh.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.blog.dto.ReplyRequestDto;
import com.kkh.blog.model.Board;
import com.kkh.blog.model.Reply;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.BoardRepository;
import com.kkh.blog.repository.ReplyRepository;
import com.kkh.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired	
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void writeService(Board board,User user) {
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional
	public void deleteService(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void updateService(int id,Board updateboard) {
		Board board =boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 업데이트실패");
		});	 
		board.setTitle(updateboard.getTitle());
		board.setContent(updateboard.getContent());
		//service 종료될때 트랜잭션이 종료된다. 이때 더티체킹이 발생하면서 자동업데이트가됨. db commit자동
		/* boardRepository.save(board); */
	}
	
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board detail(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("상세보기실패");
		});
	}
	
	@Transactional
	public void writeReply(ReplyRequestDto replyRequestDto) {
		User user = userRepository.findById(replyRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 등록실패");
		});
		
		Board board = boardRepository.findById(replyRequestDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 등록실패");
		});
		
		Reply reply = new Reply();
		reply.update(user, board, replyRequestDto.getContent());
		replyRepository.save(reply);
	}
	
	@Transactional
	public void deleteReply(int id) {
		replyRepository.deleteById(id);
	}
	
	/*
	 * @Transactional public void writeReply(int boardId, Reply requestReply, User
	 * user) { Board board = boardRepository.findById(boardId).orElseThrow(()->{
	 * return new IllegalArgumentException("댓글 등록실패"); });
	 * requestReply.setUser(user); requestReply.setBoard(board);
	 * replyRepository.save(requestReply); }
	 */
	
}
