package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.UserDAO;
import com.example.project.dto.UserDTO;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	// 로그인
	public String login(String id, String pwd) {
		UserDTO userDTO = userDAO.getUserById(id);
		if(userDTO.getPwd().equals(pwd))
			return userDTO.getId();
		return null;	
	}
	
	// 아이디 중복체크
	public boolean getId(UserDTO userDTO) {
		int n = userDAO.getId(userDTO);
		return n>0 ? true : false;
	}
	
	// 회원가입
	public boolean signup(UserDTO userDTO) {
		System.out.println("signup:"+userDTO);
		
		int n = userDAO.insertUser(userDTO);
		
		return n>0 ? true : false;
	}
	/*
	public int signup(UserDTO userDTO) {
		System.out.println("signup:"+userDTO);
		return userDAO.insertUser(userDTO);
	}
	*/
	
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

}

