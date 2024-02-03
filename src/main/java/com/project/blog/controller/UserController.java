package com.project.blog.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.model.KakaoProfile;
import com.project.blog.model.OAuthToken;
import com.project.blog.model.User;
import com.project.blog.service.UserService;

/**
 * 인증이 안된 사용자들이 접속할 수 있는 경로 /auth/**
 * 주소가 / 이면 index.jsp 허용
 * static이하에 있는 /js/**, /images/**, /css/** 허용
 * */

@Controller
public class UserController {
	
	@Value("${blog.key}")
	private String blogKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping(value="/home")
	public String home() {
		return "home"; 
	}

	@GetMapping(value="/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping(value="/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping(value="/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping(value="/auth/kakao/callback")
	public String kakaoCode(@RequestParam String code) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "5d15758a5c0bd030b48d7c8b8a0988dc");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRquest = new HttpEntity(params, headers);
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, 
				kakaoTokenRquest, 
				String.class);

		// ObjectMapper
		ObjectMapper objMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<String> kakaoUserInfo = kakaoUserInfo(oauthToken);
		System.out.println(kakaoUserInfo);
		
		return "redirect:/board";
	}
	
	public ResponseEntity<String> kakaoUserInfo(OAuthToken oauthToken) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
		ResponseEntity<String> response = rt.exchange(
					"https://kapi.kakao.com/v2/user/me",
					HttpMethod.POST,
					kakaoProfileRequest,
					String.class);
		
		ObjectMapper objMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(blogKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		User existUser = kakaoExistInfo(kakaoProfile);
		if(existUser.getUsername() == null) {
			
			userService.join(kakaoUser);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), blogKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return response;
	}
	
	public User kakaoExistInfo(KakaoProfile kakaoProfile) {
		
		User existUser = userService.ExistUser(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		
		return existUser;
	}
}
