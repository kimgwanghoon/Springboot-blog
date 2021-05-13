package com.kkh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository userRepository;
	
	@Transactional
	public void save(User user) {
			userRepository.save(user);
	}
	
	@Transactional(readOnly = true) // select 할때 트랜잭션 시작,서비스종료시에 트랜잭션 종료(정합성)
	public User login(User user) {
			return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}	
}
