package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;

@Controller
public class MypageController {

	@Autowired
	private UserService userService;

	// ################## MemberProfile ##################

	// 세션에 저장된 id값에 해당되는 유저의 정보 출력
	@GetMapping("/mypage/memberProfile")
	public String memberProfile(HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");
		if (id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			return "./mypage/memberProfile";
		}
		return "redirect:./login";
	}

	// ################## MemberUpdate ##################

	// 회원정보 수정 화면단
	@GetMapping("/mypage/memberUpdate") // //mypage/member
	public String toUpdatePage(HttpSession session, Model model) { // 회원정보 수정 페이지
		System.out.println("회원정보 수정 페이지");
		String id = (String) session.getAttribute("userId");
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		return "./mypage/memberUpdate";
	}

	// 회원정보 수정
	@PostMapping("/mypage/memberUpdate")
	public String modifyInfo(HttpSession session, UserDTO userDTO) { // 회원정보 수정
		System.out.println("업데이트" + userDTO);
		String id = (String) session.getAttribute("userId");
		userDTO.setId(id);
		System.out.println("업데이트" + userDTO);
		userService.modifyInfo(userDTO);
		System.out.println("컨트롤러");
		return "redirect:./mypage/memberProfile";
	}

	// ################## MemberDelete ##################

	// 회원 정탈퇴 페이지 화면단
	@GetMapping("/mypage/memberDelete")
	public String toDeletePage(HttpSession session, Model model) { // 회원정보 수정 페이지

		System.out.println("회원정보 수정 페이지");
		String id = (String) session.getAttribute("userId");
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		return "./mypage/memberDelete";

	}

	// 회원탈퇴
	@PostMapping("/delete")
	public String withdraw(HttpSession session) { // 탈퇴

		System.out.println("탈퇴할 계정 세션을 가져옴");
		String id = (String) session.getAttribute("userId");
		if (id != null) {
			userService.withdraw(id);
			System.out.println("회원 탈퇴");
		}
		session.invalidate();
		System.out.println("세션삭제완료_login창 리턴");
		return "redirect:/login";
	}

	// ################## Password Check ##################

	// 회원수정시 비밀번호 체크
	@GetMapping("/mypage/memberUpdateCheck")
	public String memberUpdateCheck(HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");
		if (id != null) {
			return "./mypage/memberUpdateCheck";
		}
		return "redirect:./login";
	}

	// 회원 탈퇴시 비밀번호 체크
	@GetMapping("/mypage/memberDeleteCheck")
	public String memberDeleteCheck(HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");
		if (id != null) {
			return "./mypage/memberDeleteCheck";
		}
		return "redirect:./login";
	}

	// 회원 수정/탈퇴시 비밀번호 중복 체크
	@ResponseBody
	@PostMapping("/pwdChk")
	public String getpwd(UserDTO userDTO) {
		System.out.println("아이디 컨트롤러에 POST 전달");

		boolean b = userService.getpwd(userDTO);
		System.out.println(b);
		if (b) {
			System.out.println("OK");
			return "OK"; // 1이상이라는 말 => 비밀번호가 일치한다는 말
		}
		return "NO";
	}

	// ################## MemberPasswordUpdate ##################

	// 비밀번호 변경 화면단
	@GetMapping("/mypage/memberPasswordUpdate")
	public String memberPasswordUpdate(HttpSession session, Model model) {

		String id = (String) session.getAttribute("userId");
		if (id != null) {
			return "./mypage/memberPasswordUpdate";
		}
		return "redirect:./login";
	}

}
