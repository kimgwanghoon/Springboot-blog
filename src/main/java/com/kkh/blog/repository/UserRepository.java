package com.kkh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkh.blog.model.User;

//spring 기동시 자동으로 bean 등록이되어 @Repository 생략가능해진다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
