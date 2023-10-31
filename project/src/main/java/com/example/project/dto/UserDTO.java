package com.example.project.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String pwd;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	
	@NotBlank(message = "학교를 선택하세요.")
	
	private String education;
	private String school;
	private String education_code;
	private String school_code;
	
	@NotBlank(message = "학년을 선택하세요.")
	private String usergrade;
	
	@NotBlank(message = "반을 선택하세요.")
	private String userclass; 

	
}
	
	