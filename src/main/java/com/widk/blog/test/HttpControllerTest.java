package com.widk.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(html 파일) -> 
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest";
	
	// localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "widk", "1234", "widk@naver.com");
		Member m = Member.builder().username("www").password("2222").email("gmail").build();	// builder를 사용하면 필드의 순서,갯수 안지켜도 상관 x.
		System.out.println(TAG+"getter : "+ m.getUsername() );
		m.setUsername("widk");
		System.out.println(TAG+"getter : "+ m.getUsername() );
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	// http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) { // 쿼리스트링 id=1&username=widk&password=1234&email=widk@naver.com / MessageConverter(스프링부트)
		return "get 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/post(insert)
	@PostMapping("/http/post")	// text/plain , application/json
	public String postTest(@RequestBody Member m) {	// MessageConverter(스프링부트)
			return "post 요청 : "+ m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 :" +  m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
			return "delete 요청";
	}
	
}
