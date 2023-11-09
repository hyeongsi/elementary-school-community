package com.example.project.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
	public String memberProfile(Principal principal,@Valid Authentication authentication,HttpSession session, Model model) {
		if(principal!=null) {
			model.addAttribute("loginCheck", 1);
		} else {
			model.addAttribute("loginCheck", 0);
		}
		System.out.println(model.getAttribute("loginCheck")+"로그인체크");
		System.out.println("메인페이지");
		String id = (String)session.getAttribute("userId");
		System.out.println("세션값 변수에 저장");
		
		if((int)model.getAttribute("loginCheck") == 1) {
			
		PrincipalUser userDetails = (PrincipalUser)authentication.getPrincipal();
		String userId = userDetails.getUsername();
		String userEO = userService.userInfo(userId).getOfficeOfEducationCode().toString();
		UserBoardIdMapping cal = new UserBoardIdMapping();	// 유저 보드아이디를 시도교육청 코드로 변환
		model.addAttribute("top5", homePageService.mainPageTop5(cal.boardIdMapping(userEO)));
		}
		
		System.out.println(homePageService.mainPagePublicTop5());
		model.addAttribute("publicTop5", homePageService.mainPagePublicTop5());
		
		if(id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			System.out.println("모델에 로그인 정보 저장");
			return "index";
		}
		return "index";
	}
	
}
