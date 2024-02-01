package com.project.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfile {

	private Long id;
	private Properties properties;
	private KakaoAccount kakao_account;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Properties {
		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class KakaoAccount {
		private String email;
	}
}
