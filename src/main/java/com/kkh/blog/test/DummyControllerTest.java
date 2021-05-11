package com.kkh.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired	//스프링이 관리하고있는 UserRepository가 있다면 사용해라. DI 동작 
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(((User) user).getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		user.setRole(RoleType.user);
		userRepository.save(user);
		return "회원가입완료";
	}
}
