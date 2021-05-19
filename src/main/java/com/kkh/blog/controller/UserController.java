package com.kkh.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkh.blog.model.OAuthToken;
import com.kkh.blog.model.OAuthType;
import com.kkh.blog.model.User;
import com.kkh.blog.model.KakaoProfile;
import com.kkh.blog.service.UserService;

//주소가 "/" 이거나 /auth/ 하단의 경로는 인증이 안된 사용자도 사용할 수 있도록 허용, 그 외 경로는 인증된 사용자만 접근하도록 
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Value("${blog.pwd}")
//	private String key;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/loginFail")
	public String loginFail() {
		return "user/loginFail";
	}
	
	@GetMapping("/auth/kakao/callback")
	public  String kakaoLogin(String code) {
		String grant_type="authorization_code", client_id = "1a17f2259b3ddffc87c28860c51feba7";
		String redirect_uri="http://localhost:8000/auth/kakao/callback";
		//POST 방식으로 key=value 카카오에 데이터를 요청
		RestTemplate rt = new RestTemplate();
		HttpHeaders header = new HttpHeaders();	//httpheader생성
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", grant_type);
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params,header);		//HttpDeader와 HttpBody를 하나의 오브젝트에 담기 
		//Http POST 방식으로 요청, response 변수의 응답 받기
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",HttpMethod.POST,kakaoRequest,String.class);
		
		ObjectMapper objectMapper = new  ObjectMapper();
		OAuthToken oauthToken=null;
		try {
			 oauthToken= objectMapper.readValue(response.getBody(), OAuthToken.class);
		}catch(JsonMappingException e) {
			e.printStackTrace();
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		KakaoProfile userfile = kakaoUser(oauthToken.getAccess_token());
		String pwd = "kakao"+userfile.getId().toString();
		User kakaoUser = User.builder()
				.username("kakao_"+userfile.getKakao_account().getEmail())
				.password(pwd)
				.email(userfile.getKakao_account().getEmail())
				.oauth(OAuthType.kakao)
				.build();
		//기존 회원가입 체크
		User findUser = userService.findUser(kakaoUser.getUsername());
		if (findUser.getUsername()==null) {
			userService.save(kakaoUser);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), pwd));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
	
	public KakaoProfile kakaoUser(String accessToken) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders header = new HttpHeaders();	//httpheader생성
		header.add("Authorization", "Bearer "+accessToken);
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(header);		//HttpDeader와 HttpBody를 하나의 오브젝트에 담기 
		//Http POST 방식으로 요청, response 변수의 응답 받기
		ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me",HttpMethod.POST,kakaoProfileRequest,String.class);
		
		ObjectMapper objectMapper = new  ObjectMapper();
		KakaoProfile kakaoProfile=null;
		try {
			kakaoProfile= objectMapper.readValue(response.getBody(), KakaoProfile.class);
		}catch(JsonMappingException e) {
			e.printStackTrace();
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return kakaoProfile;
	}
	
}
