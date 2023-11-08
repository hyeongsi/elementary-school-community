package com.example.project.service;

import com.example.project.dao.UserDAO;
import com.example.project.dto.SchoolInfo;
import com.example.project.dto.UserDTO;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
@AllArgsConstructor
public class UserService {
	
	UserDAO userDAO;
	PasswordEncoder passwordEncoder;

	// 로그인
	public String login(String id, String pwd) {
		
		System.out.println("서비스");
		
		UserDTO userDTO = userDAO.getUserById(id);
		if(userDTO != null) {
			if(userDTO.getPwd().equals(pwd)) {
				return userDTO.getId();
			}
		}
		return null;
		
	}
	
	// 아이디 중복체크
	public boolean getId(UserDTO userDTO) {
		int n = userDAO.getId(userDTO);
		System.out.println(n);
		return n>0 ? true : false;
	}
	
	// 이메일 중복체크
		public boolean getEmail(String email) {
			int n = userDAO.getEmail(email);
			System.out.println(n);
			return n>0 ? true : false;
		}
		
		
	// 암호화된 비밀번호 가져옴
	public String getEncordpwd(String id) {
		
		UserDTO userDTO = userDAO.getEncordpwd(id);
		
		String EncordPwd = userDTO.getPwd();
		
		return EncordPwd;
	}
	
	// 회원가입
	public int signup(UserDTO userDTO) {
		System.out.println("signup:"+userDTO);
		return userDAO.insertUser(userDTO);
	}
	
	
	// 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
	
	
	
	// 내정보
	public UserDTO getUserById(String id) {
		return userDAO.getUserById(id);
	}
	
	// 회원정보 수정
	public int modifyInfo(UserDTO userDTO) {
		System.out.println("서비스앞");
		return userDAO.updateUser(userDTO);
	}
	
	// 비밀번호 수정
	public int updatePwd(String id, String changepwd) {
		System.out.println("비밀번호 변경 서비스");
		
		String EncordPwd = passwordEncoder.encode(changepwd);
		
		return userDAO.updatePwd(id, EncordPwd);
	}

	// 회원탈퇴
	public void withdraw(String id) {
		userDAO.deleteUser(id);
	}
	
	// 회원 학교 정보 얻기
	public SchoolInfo userInfo(String id) {
		return userDAO.userInfo(id);
	}
	
	
	
	
	
	
	
	
	
	
	

}

