package com.kkh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kkh.blog.model.User;

//spring 기동시 자동으로 bean 등록이되어 @Repository 생략가능해진다.
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ? AND password = ?; 로 동작함
	User findByUsernameAndPassword(String username, String password);
	
	/* 위방식과 아래방식 동작은 같음
	 * @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery=true)
	 *  User login(String username, String password);
	 */
}
