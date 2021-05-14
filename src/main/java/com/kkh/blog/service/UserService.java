package com.kkh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(RoleType.user);	
		userRepository.save(user);
	}
}
