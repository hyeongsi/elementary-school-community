package com.example.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.SchoolInfo;
import com.example.project.dto.UserDTO;

@Mapper
@Repository
public interface UserDAO {
	
	// 로그인 / 내정보
	public UserDTO getUserById(String id);
	
	// 아이디 중복체크
	public int getId(UserDTO userDTO);
	
	// 비밀번호 중복체크
	public int getpwd(UserDTO userDTO);
	
	// 회원가입
	public int insertUser(UserDTO userdto);
	
	// 회원정보 수정
	public int updateUser(UserDTO userDTO);
	
	//회원 탈퇴
	public void deleteUser(String id);
	
	// 이메일로 아이디값 받아오기
	public UserDTO findUserByUserId(String email);

	// 매칭된 아이디의 비밀번호 변경
	public void updateUserPassword(String id, String pwd);
	

	//회원 학교 정보 얻기
	public SchoolInfo userInfo(String id);
	

	// 암호화된 비밀번호 가져옴
	public UserDTO getEncordpwd(String id);


}
