package com.example.project.controller;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

	// @Autowire
	final UserService userService;
	final PasswordEncoder passwordEncoder;

	// 로그인 성공
	@GetMapping("/loginSuccess")
	public String loginSuccess(Principal principal, HttpSession session, Model model) { // 로그인

		UserDTO userDTO = userService.getUserById(principal.getName());

		if (userDTO == null) { // 로그인 실패
			model.addAttribute("loginFail", "아이디 혹은 비밀번호를 확인해주세요");
			return "/login";
		}

		session.setAttribute("userId", principal.getName());
		session.setAttribute("user", userDTO);
		return "redirect:/";

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
			// 암호화 진행
			userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
			userService.signup(userDTO);

		} catch (DuplicateKeyException e) {
			return "redirect:/signup?error_code=-1";    
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/signup?error_code=-99";
		}

		return "redirect:/login";
	}

}
