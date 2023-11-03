package com.example.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;

@Controller
public class HomeController {

	@Autowired
	 private UserService userService;
	
	@GetMapping("/")
	public String memberProfile(HttpSession session, Model model) {
		System.out.println("메인페이지");
		String id = (String)session.getAttribute("userId");
		System.out.println("세션값 변수에 저장");
		if(id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			System.out.println("모델에 로그인 정보 저장");
			return "index";
		}
		return "index";
	}
	
}
