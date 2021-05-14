package com.kkh.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kkh.blog.auth.PrincipalDetail;
import com.kkh.blog.service.PrincipalDetailService;

@EnableWebSecurity	//시큐리티 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근시 권한, 인증을 체크
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //csrf 토큰 비활성화
		.authorizeRequests().antMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll()
		.anyRequest().authenticated()	//지정한 페이지 외의 다른 모든요청은 인증이 필요하다.
		.and().formLogin().loginPage("/auth/loginForm")	//권한,인증이 필요한 페이지 접근시 .loginPage에 지정한페이지로 이동
		.loginProcessingUrl("/auth/procLogin").defaultSuccessUrl("/");	//시큐리티가 해당주소로 오는 요청을 가로채서 대신 로그인진행
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(enc());
	}
	
	@Bean
	public BCryptPasswordEncoder enc() {
		return new BCryptPasswordEncoder();
	}
}
