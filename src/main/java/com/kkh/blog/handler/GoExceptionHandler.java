package com.kkh.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GoExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handIllegalException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h>";
	}
}
