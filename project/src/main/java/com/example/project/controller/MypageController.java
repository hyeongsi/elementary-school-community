package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;

@Controller
//@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	 private UserService userService;
	
	@GetMapping("/mypage/memberProfile")
	public String memberProfile(HttpSession session, Model model) {
		System.out.println("마이페이지");
		String id = (String)session.getAttribute("userId");
		if(id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			System.out.println("후"+userDTO);
			return "mypage/memberProfile";
		}
		return "redirect:./login";
	}
	
	// 수정 비밀번호체크
	@GetMapping("/mypage/memberUpdateCheck")
	public String memberUpdateCheck(HttpSession session, Model model) {
		System.out.println("비밀번호 체크페이지");
		
		String id = (String)session.getAttribute("userId");
		if(id != null) {
			return "mypage/memberUpdateCheck";
		}
		return "redirect:./login";
	}
	
	//탈퇴 비밀번호 체크
	@GetMapping("/mypage/memberDeleteCheck")
	public String memberDeleteCheck(HttpSession session, Model model) {
		System.out.println("비밀번호 체크페이지");
		
		String id = (String)session.getAttribute("userId");
		if(id != null) {
			return "mypage/memberDeleteCheck";
		}
		return "redirect:./login";
	}
	
	
	
	
	// 비밀번호 수정
		@GetMapping("/mypage/memberPasswordUpdate")
		public String memberPasswordUpdate(HttpSession session, Model model) {
			System.out.println("비밀번호 변경 페이지");
			
			String id = (String)session.getAttribute("userId");
			if(id != null) {
				return "mypage/memberPasswordUpdate";
			}
			return "redirect:./login";
		}
		
		
		
		
		
		
		
		
		
		
}
