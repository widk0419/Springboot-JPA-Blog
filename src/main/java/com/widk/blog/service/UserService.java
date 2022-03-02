package com.widk.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.widk.blog.model.RoleType;
import com.widk.blog.model.User;
import com.widk.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encodedr;
	
	// 회원가입
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();	// 비밀번호 원문
		String encPassword = encodedr.encode(rawPassword);	// 해쉬화된 비밀번호
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);	// 입력 데이터,id,createdate 는 알아서 insert 되기 때문에 role만 강제로 user로 insert 해줌.
		userRepository.save(user);
	}
	// 로그인
	// 전통적인 방식의 스프링 로그인 구현
	//	@Transactional(readOnly = true)	// Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	//	public User 로그인(User user) {
	//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword() );
	//	}
}
