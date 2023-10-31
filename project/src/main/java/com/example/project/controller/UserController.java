package com.example.project.controller;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
    public String dispSignup(UserDTO userDTO) {
        return "login";
    }
	
	
	// 로그인 성공
	@GetMapping("/loginSuccess")
	public String loginSuccess(Principal principal, HttpSession session, Model model) { // 로그인

		UserDTO userDTO = userService.getUserById(principal.getName());

		if (userDTO == null) { // 로그인 실패
			model.addAttribute("loginFail", "아이디 혹은 비밀번호를 확인해주세요");
			return "/login";
		}

		userDTO.setPwd(null);
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
	public String signup(@Valid UserDTO userDTO, Errors errors, Model model) {
		if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("UserDTO", userDTO);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "login"; // 유효성 검사 오류가 발생하면 다시 회원가입 폼으로 이동
		}
		
		// 중복검사
		/*
		userService.checkUsernameDuplication(userDTO);
		userService.checkNicknameDuplication(userDTO);
		userService.checkEmailDuplication(userDTO);
		*/
		
		userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
		userService.signup(userDTO);
		return "redirect:/login";
	}

}


