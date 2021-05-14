package com.kkh.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	//loadUserByUsername를 오버라이딩을 하지않으면 사용자정보를 담아줄수 없다. 스프링이 실행될때  기본 시큐리티사용자가 셋팅된다.
	//로그인 요청을 가로챌때 변수2개(username,password)를 가져가는데 password부분처리는 알아서함, username이 DB에 있는지 확인하면됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(()->{
						return new UsernameNotFoundException("사용자를 찾을수 없습니다.");
		});
		return new PrincipalDetail(principal);	//시큐리티 세션에 사용자정보저장, 오버라이딩 하지않으면 PrincipalDetail()로  리턴
	}
}
