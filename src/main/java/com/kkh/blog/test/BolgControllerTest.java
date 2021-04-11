package com.kkh.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 //스프링이 com.cos.blog 패키지 이하 스캔해서 모든파일 메모리에 관리X, 특정 어노테이션이 붙은 클래스를 new해서(IoC) 스프링 컨테이너에 관리
@RestController
public class BolgControllerTest {
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>spring boot</h1>";
	}
}
