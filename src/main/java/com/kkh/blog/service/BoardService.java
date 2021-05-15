package com.kkh.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.blog.model.Board;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired	
	private BoardRepository boardRepository;
	
	@Transactional
	public void writeService(Board board,User user) {
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional
	public void deleteService(int id) {
		boardRepository.deleteById(id);
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
}
