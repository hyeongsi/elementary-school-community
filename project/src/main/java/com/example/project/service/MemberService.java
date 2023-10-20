package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.dao.MemberDAO;
import com.example.project.dto.MemberDTO;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	MemberDAO dao;
		
	public boolean getId(MemberDTO dto) {
		int n = dao.getId(dto);
		
		return n>0?true:false;
	}
	
	
	public boolean addmember(MemberDTO dto) {
		int n = dao.addmember(dto);
		
		return n>0?true:false;
	}
	
	public MemberDTO login(MemberDTO dto) {
		
		return dao.login(dto);
	}
}
