package com.widk.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.widk.blog.model.RoleType;
import com.widk.blog.model.User;
import com.widk.blog.repository.UserRepository;

// html이 아니라 데이터를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTEst {
	
	@Autowired	// 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패. 해당 id는 DB에 존재하지 않습니다.";
		}
	
		return "삭제 되었습니다. id :" + id;
	}
	
	// save 함수는 id를 전달하지 않으면 insert를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌.
	// email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id :" + id);
		System.out.println("password :" + requestUser.getPassword() );
		System.out.println("email :" + requestUser.getEmail() );
		
		User user = userRepository.findById(id).orElseThrow( ()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user);
		
		// 더티 체킹
		// 영속성 컨택스트(Persistence Context) 안에 있는 엔티티를 대상으로 더티 체킹이 일어납니다.
		// Transaction 안에서 엔티티의 변경이 일어나면, 변경 내용을 자동으로 데이터베이스에 반영

		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	// 가입되어 있는 유저를 리스트로 볼수있는  
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건에 데이터를 리터받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable ){
		List<User> users = userRepository.findAll(pageable).getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾게 될 경우 데이터베이스에 id=4인 데이터가 없다면 user가 null이 되기 때문에
		// return 값이 null 이 되게 되고 그럼 문제가 발생하게 됨.
		// 따라서 optional로 user 객체를 감싸서 가져올테니 null 인지 아닌지 알아서 판단해서 return 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당유저는 존재하지 않습니다. id :"+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 : 자바 오브젝트
		// 변환 : (웹브라우저가 이해 가능한 데이터) -> json(Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동으로 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json타입으로 변환해서 브라우저에 반환함.
		return user;
		
		// 람다식
//		User user = userRepository.findById(id).orElseThrow( ()->{
//			return new IllegalArgumentException("해당유저는 존재하지 않습니다. id :"+id);
//		});
//		return user;
	}
	
	

	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username,password,email 데이터를 가지고 (요청)
	
//	public String join(String username, String password, String email) {	// key = value (약속된 규칙)
//		
//		System.out.println("username :" + username);
//		System.out.println("password :" + password);
//		System.out.println("email :" + email);
//		
//		return "회원가입 완료";
//	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id :" + user.getId() );
		System.out.println("username : " + user.getUsername() );
		System.out.println("password : " +user.getPassword() );
		System.out.println("email : " +user.getEmail() );
		System.out.println("role : "+user.getRole() );
		System.out.println("createdate : "+user.getCreateDate() );
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
	
}
