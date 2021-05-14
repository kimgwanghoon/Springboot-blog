package com.kkh.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kkh.blog.model.User;

//스프링 시큐리티가 로그인을 완료하면 UserDetails 타입의 오브젝트를 시큐리티의 고유한 세션저장소에 저장
public class PrincipalDetail implements UserDetails{
	private User user;	//다른객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 메서드 호출기법 : 컴포지션
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	//계정의 권한목록을 리턴한다. 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{ return "ROLE_"+user.getRole(); });
		return collectors;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료 체크하여 리턴(true:계정활성)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 체크
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성상태를 리턴
	@Override
	public boolean isEnabled() {
		return true;
	}
}
