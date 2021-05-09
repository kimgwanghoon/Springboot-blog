package com.kkh.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class httpContollerTest {
	//웹에서 호출시 get으로만 호출이 가능하다. post,put,delete는 웹호출시 에러발생
	@GetMapping("/http/get") 
	public String getTest(Member m) { //?id=1&username=dnke  //MessageConverter(스프링부트)
		return "get 요청 : " + m.getId( )+", "+m.getUsername();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {	//MessageConverter(스프링부트)에서 자동으로 파싱해서 오브젝트에 넣어준다.
		return "post 요청"+ m.getId( )+", "+m.getUsername()+", "+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {	//MessageConverter(스프링부트)에서 자동으로 파싱해서 오브젝트에 넣어준다.
		return "put 요청"+ m.getId( )+", "+m.getUsername()+", "+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {	//MessageConverter(스프링부트)에서 자동으로 파싱해서 오브젝트에 넣어준다.
		return "delete 요청"+ m.getId( )+", "+m.getUsername()+", "+m.getEmail();
	}

}
