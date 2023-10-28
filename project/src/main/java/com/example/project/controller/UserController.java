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
	
	
	
	// 로그인상태 체크 로직 ==> 수정
	@GetMapping("/login")
	public String toLoginPage(HttpSession session) {
		
		String id = (String) session.getAttribute("userId");
		System.out.println("로그인 페이지");
		
		if (id != null) { // 로그인된 상태
			System.out.println("로그인 상태");
			return "redirect:./mypage/memberProfile";
		}
		System.out.println("미로그인 상태");
		return "login"; // 로그인 되지않은 상태
		
	}
	
	// 로그인 버튼 클랙시 산태변경 로직
	@PostMapping("/login")
	public String login(String id, String pwd, HttpSession session) { // 로그인
		
		String userId = userService.login(id, pwd);
		
		System.out.println("로그인 서비스 삽입");
		if(userId == null) { // 로그인 실패
			return "redirect:./login";
		}
		
		System.out.println("세션저장");
		session.setAttribute("userId", userId);
		return "redirect:./";
		
	} 
	
	
	// 회원가입 - 아이디 중복 체크
	@ResponseBody
	@PostMapping("/getId")
	public String getId(UserDTO userDTO) {
		System.out.println("아이디 컨트롤러에 POST 전달");
		
		boolean b = userService.getId(userDTO);
		System.out.println(b);
		if(b) {
			return "NO"; // 1이상이라는 말 => 이미 아이디가 존재한다는 말이라 사용 불가능
		}
		System.out.println("OK");
		return "OK";
	}
	
	
	// 회원가입 로직
	@PostMapping("/signup")
	public String signup(UserDTO userDTO) { // 화원가입
		try {
			userService.signup(userDTO);
		}catch(DuplicateKeyException e) {
			return "redirect:/signup?error_code=-1";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/signup?error_code=-99";
		}
		
		return "redirect:/login";
	}
	
	
	// 회원정보 수정
	@GetMapping("/mypage/memberUpdate")// //mypage/member
	public String toUpdatePage(HttpSession session, Model model) { // 회원정보 수정 페이지
		System.out.println("회원정보 수정 페이지");
		String id = (String) session.getAttribute("userId");
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		return "mypage/memberUpdate";
	}
	
	@PostMapping("/mypage/memberUpdate")
	public String modifyInfo(HttpSession session, UserDTO userDTO) { // 회원정보 수정
		System.out.println("업데이트"+userDTO);
		String id = (String)session.getAttribute("userId");
		userDTO.setId(id);
		System.out.println("업데이트"+userDTO);
		userService.modifyInfo(userDTO);
		System.out.println("컨트롤러");
		return "redirect:./memberProfile";
	}
	
	// 로그아웃
	@PostMapping("/logout")
	public String logout(HttpSession session) { // 로그아웃
		session.invalidate();
		System.out.println("세션 로그아웃");
		return "redirect:./";
	}
	
	
	// 회원 정탈퇴 페이지
	@GetMapping("/mypage/memberDelete")
	public String toDeletePage(HttpSession session, Model model) { // 회원정보 수정 페이지
		System.out.println("회원정보 수정 페이지");
		String id = (String) session.getAttribute("userId");
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		return "mypage/memberDelete";
	}
	
	// 회원탈퇴
	@PostMapping("/mypage/delete")
	public String withdraw(HttpSession session) { // 탈퇴
		System.out.println("탈퇴할 계정 세션을 가져옴");
		String id = (String) session.getAttribute("userId");
		if(id != null) {
			userService.withdraw(id);
			System.out.println("회원 탈퇴");
		}
		session.invalidate();
		System.out.println("세션삭제완료_login창 리턴");
		return "redirect:/login";
	}
	
	
}








