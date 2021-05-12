package com.kkh.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.blog.model.RoleType;
import com.kkh.blog.model.User;
import com.kkh.blog.repository.UserRepository;




@RestController
public class DummyControllerTest {

	@Autowired	//스프링이 관리하고있는 UserRepository가 있다면 사용해라. DI 동작 
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id를 전달해서 id가 존재한다면 id에 대한 데이터를 update를 수행, id가 없으면 insert해준다.
	@Transactional	//save를 하지않아도 update가 수행된다.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) { //json 데이터를 요청하면 MessageConverter의  Jackson lib가 변환해서 받아준다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(requestUser);
		//더티 체킹
		return null;
	}
	
	//한페이지당 n건에 데이터를 리턴
	//List반환시 데이터만, Page반환시 데이터와 페이지정보값을 반환한다. 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser=userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라메터를 전달받을수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
/*		
 		DB에서 id에 해당하는 사용자를 못찾으면 null이 return이 되므로 프로그램에 문제가 생길수 있으니
		Optional로 User객체를 감싸서 데이터를 가져오면 null인지 아닌지 판단해서 return할수 있도록 해준다.
		userRepository.findById(id).get() 은 null일리가 없으니 그냥 반환해주도록 하는것
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
*/	
		//람다식
		User user = userRepository.findById(id).orElseThrow(()->{
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			});
		//스프링부트에서 MessageConverter라는 것이 응답시에 자동작동하여 만약 자바 오브젝트를 리턴하게되면 Jackson lib를 호출해서 user오브젝트를 json형태로 브라우저에 던져준다.
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.user);
		userRepository.save(user);
		return "회원가입완료";
	}
}
