package com.example.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.project.config.auth.PrincipalUser;
import com.example.project.dto.UserDTO;
import com.example.project.service.HomePageService;
import com.example.project.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private HomePageService homePageService;
	
	@GetMapping("/")
	public String memberProfile(Authentication authentication,HttpSession session, Model model) {
		System.out.println("메인페이지");
		String id = (String)session.getAttribute("userId");
		System.out.println("세션값 변수에 저장");
		PrincipalUser userDetails = (PrincipalUser)authentication.getPrincipal();
		String userId = userDetails.getUsername();
		String userEO = userService.userInfo(userId).getOfficeOfEducationCode().toString();
		UserBoardIdMapping cal = new UserBoardIdMapping();
		model.addAttribute("top5", homePageService.mainPageTop5(cal.boardIdMapping(userEO)));
		if(id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			System.out.println("모델에 로그인 정보 저장");
			return "index";
		}
		return "index";
	}
	
}
