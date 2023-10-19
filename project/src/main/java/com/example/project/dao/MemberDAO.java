package com.example.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.MemberDTO;



@Mapper
@Repository
public interface MemberDAO {
	
	//반드시 xml에 있는 변수명과 일치해야한다.
	//public List<MemberDTO> allMember(); 
	
	public int getId(MemberDTO dto);
	
	public int addmember(MemberDTO dto);
	
	// 로그인
	public MemberDTO login(MemberDTO dto);
	
}
