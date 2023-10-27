package com.example.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;

@Controller
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
			return "mypage/memberProfile";
		}
		return "redirect:./login";
	}
	
}
