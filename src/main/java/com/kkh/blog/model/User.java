package com.kkh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
//ORM -> Java Object -> 테이블로 매핑해주는 기술
@Entity	//User클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; //시퀀스, auto_increment
	
	@Column(unique = true ,nullable=false,length=50)
	private String username;
	@Column(nullable=false,length=100)		//해쉬로 비밀번호 암호화하여 저장
	private String password;
	@Column(nullable=false,length=50)
	private String email;
	
	//@ColumnDefault("user")
	@Enumerated(EnumType.STRING) //DB에는 RoleType라는 타입이 없으므로 String타입이라는걸 알려줘야한다.
	private RoleType role; //Enum을 쓰면 좋다. //admin,user
	
	@Enumerated(EnumType.STRING) 
	private OAuthType oauth; 
	
	@CreationTimestamp	//시간이 자동입력
	private Timestamp createDate;
}
