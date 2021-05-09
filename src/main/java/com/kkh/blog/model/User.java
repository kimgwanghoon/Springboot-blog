package com.kkh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity	//User클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; //시퀀스, auto_increment
	
	@Column(nullable=false,length=30)
	private String username;
	@Column(nullable=false,length=100)		//해쉬로 비밀번호 암호화하여 저장
	private String password;
	@Column(nullable=false,length=50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; //Enum을 쓰면 좋다. //admin,user,manager
	
	@CreationTimestamp	//시간이 자동입력
	private Timestamp createDate;
}
