package com.kkh.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GoExceptionHandler {
	
	/*
	 * @ExceptionHandler(value=IllegalArgumentException.class) public String
	 * handIllegalException(IllegalArgumentException e) { return
	 * "<h1>"+e.getMessage()+"</h>"; }
	 */
	
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleException(Exception e){
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	
	
}
