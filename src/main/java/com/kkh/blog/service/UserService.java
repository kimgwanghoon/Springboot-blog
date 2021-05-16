package com.kkh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.blog.model.Board;
import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@Transactional
	public void save(User user) {
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		user.setRole(RoleType.user);	
		userRepository.save(user);
	}
	
	@Transactional
	public void update(User userUpdate) {
		User user = userRepository.findById(userUpdate.getId()).orElseThrow(()->{
			return new IllegalArgumentException("사용자 업데이트실패");
		});	 
		user.setPassword(PasswordEncoder.encode(userUpdate.getPassword()));
		user.setEmail(userUpdate.getEmail());
	}
}
