package com.widk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.widk.blog.model.User;


// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략이 가능.

public interface UserRepository extends JpaRepository<User, Integer>{

	
}


//JPA Naming 쿼리 전략
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
// User findByUsernameAndPassword(String username, String password);
	
// 이렇게도 사용 가능 (위랑 같은 기능)
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
//	User login(String username, String password);