package com.example.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;

@Controller
public class UserController {

	// @Autowire
	final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ################## Login ##################

	// 로그인상태 체크
	@GetMapping("/login")
	public String toLoginPage(HttpSession session, Model model) {
		String id = (String) session.getAttribute("userId");
		model.addAttribute("loginFail", "");
		if (id != null) { // 로그인된 상태

			return "redirect:./mypage/memberProfile";
		}

		return "login"; // 로그인 되지않은 상태

	}

	// 로그인 버튼 클랙시 산태변경
	@PostMapping("/login")
	public String login(String id, String pwd, HttpSession session, Model model) { // 로그인

		String userId = userService.login(id, pwd);

		if (userId == null) { // 로그인 실패

			model.addAttribute("loginFail", "아이디 혹은 비밀번호를 확인해주세요");

			return "./login";
		}

		session.setAttribute("userId", userId);
		return "redirect:./";

	}
	
	// ################## SignUp ##################

	// 회원가입 - 아이디 중복 체크
	@ResponseBody
	@PostMapping("/getId")
	public String getId(UserDTO userDTO) {

		boolean b = userService.getId(userDTO);

		if (b) {
			return "NO"; // 1이상이라는 말 => 이미 아이디가 존재한다는 말이라 사용 불가능
		}

		return "OK";
	}

	// 회원가입
	@PostMapping("/signup")
	public String signup(UserDTO userDTO, Model model) { // 화원가입
		try {
			userService.signup(userDTO);

		} catch (DuplicateKeyException e) {
			return "redirect:/signup?error_code=-1";    
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/signup?error_code=-99";
		}

		return "redirect:/login";
	}
	
	// ################## LogOut ##################

	// 로그아웃
	@PostMapping("/logout")
	public String logout(HttpSession session) { // 로그아웃
		session.invalidate();
		System.out.println("세션 로그아웃");
		return "redirect:./";
	}

}
