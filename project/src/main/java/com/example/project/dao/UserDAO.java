package com.example.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.UserDTO;

@Mapper
@Repository
public interface UserDAO {
	
	// 로그인 / 내정보
	public UserDTO getUserById(String id);
	
	// 아이디 중복체크
	public int getId(UserDTO dto);
	
	// 회원가입
	public int insertUser(UserDTO userdto);
	
	// 회원정보 수정
	public int updateUser(UserDTO userDTO);
	
	//회원 탈퇴
	public void deleteUser(String id);

}
