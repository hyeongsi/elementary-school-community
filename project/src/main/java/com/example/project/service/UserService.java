package com.example.project.service;

import com.example.project.dao.UserDAO;
import com.example.project.dto.UserDTO;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
@AllArgsConstructor
public class UserService {
	
	UserDAO userDAO;

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
	
	// 비밀번호 중복체크
		public boolean getpwd(UserDTO userDTO) {
			int n = userDAO.getpwd(userDTO);
			System.out.println(n);
			return n>0 ? true : false;
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
	
	// 회원탈퇴
	public void withdraw(String id) {
		userDAO.deleteUser(id);
	}
	
	/*
	@Transactional(readOnly = true)
	public void checkUsernameDuplication(UserDTO userDTO) {
		boolean usernameDuplicate = userRepository.existsByUsername(dto.toEntity().getUsername());
		if (usernameDuplicate) {
			throw new IllegalStateException("이미 존재하는 아이디입니다.");
		}
	}
	
	@Transactional(readOnly = true)
	public void checkNicknameDuplication(UserDTO userDTO) {
		boolean nicknameDuplicate = userRepository.existsByNickname(dto.toEntity().getNickname());
		if (nicknameDuplicate) {
			throw new IllegalStateException("이미 존재하는 닉네임입니다.");
		}
	}
	
	
	
	@Transactional(readOnly = true)
	public void checkEmailDuplication(UserDTO userDTO) {
		boolean emailDuplicate = userRepository.existsByEmail(dto.toEntity().getEmail());
		if (emailDuplicate) {
			throw new IllegalStateException("이미 존재하는 이메일입니다.");
		}
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	

}

