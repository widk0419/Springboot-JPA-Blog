package com.widk.blog.model;

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
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술.
@Entity	// User 클래스가 MySQL에 자동으로 테이블이 생성됨.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder		// 빌터 패턴
//@DynamicInsert		// insert 시에 null 값을 자동으로 제외시켜줌.
public class User {

	@Id	// 기본키(primary key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스,auto_increment
	
	@Column(nullable = false, length = 30 , unique = true)
	private String username;	// 아이디
	
	@Column(nullable = false, length = 100)		// 123456 => 해쉬(비밀번호 암호화)
	private String password;		// 패스워드
	
	@Column(nullable = false, length = 50)
	private String email;	
	
	//	@ColumnDefault("'user'")		// null 값이 들어가는 경우가 생김.
	// DB에는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;	// Enum(Domain)을 쓰는 게 좋다. // ADMIN, USER(권한)
	
	@CreationTimestamp	// 시간이 자동으로 입력
	private Timestamp createDate;
	
	
	
}
