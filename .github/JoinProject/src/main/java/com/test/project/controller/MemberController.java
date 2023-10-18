package com.test.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.project.dto.MemberDTO;
import com.test.project.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	

	@RequestMapping(value="/getId", method= {RequestMethod.GET, RequestMethod.POST})
		public String getId(MemberDTO dto) {
		System.out.println("MemberController getID");
		
		boolean b = service.getId(dto);
		if(b) {
			return "NO"; //1이상이란 말, 이미 아이디가 존재한다는 말이라서 사용이 안된다
		}
		return "OK";
	}
	
	@RequestMapping(value="/addmember", method= {RequestMethod.GET, RequestMethod.POST})
	public String addmember(MemberDTO dto) {
		System.out.println("MemberController addmember");		
		boolean b = service.addmember(dto);
		
	if(b) {
		return "YES";
	}
	return "NO";
	}
	
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public MemberDTO login(MemberDTO dto) {
		System.out.println("MemberController login");	
		
		MemberDTO mem = service.login(dto);
		return mem;
	}

}
