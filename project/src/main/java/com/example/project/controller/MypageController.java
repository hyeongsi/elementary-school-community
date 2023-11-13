package com.example.project.controller;

import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MypageController {

	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	// ################## MemberProfile ##################

	// 세션에 저장된 id값에 해당되는 유저의 정보 출력
	@GetMapping("/mypage/memberProfile")
	public String memberProfile(Principal principal, Model model) {
		
		String id = principal.getName();
		if (id != null) {
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("user", userDTO);
			return "/mypage/memberProfile";
		}
		return "redirect:/app/login";
	}

	// ################## MemberUpdate ##################

	// 회원정보 수정 화면단
	@GetMapping("/mypage/memberUpdate") // //mypage/member
	public String toUpdatePage(Principal principal, Model model) { // 회원정보 수정 페이지
		System.out.println("회원정보 수정 페이지");
		String id = principal.getName();
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		return "/mypage/memberUpdate";
	}

	// 회원정보 수정
	@PostMapping("/mypage/memberUpdate")
	@ResponseBody
	public String modifyInfo(@Valid UserDTO userDTO, Errors errors, Principal principal) { // 회원정보 수정

		if (errors.hasErrors()) {   
            System.out.println("NO");
            System.out.println("userDTO: "+userDTO);
            return "NO"; // 유효성 검사 오류가 발생하면 다시 회원가입 폼으로 이동
		}

		String id = principal.getName();
		userDTO.setId(id);
		
		System.out.println("업데이트" + userDTO);

		userService.modifyInfo(userDTO);
		
		System.out.println("컨트롤러");
		
		return "YES";
	}

	// 회원정보 수정
	@PatchMapping("/mypage/memberUpdate")
	@ResponseBody
	public String modifyInfoById(@Valid UserDTO userDTO, Errors errors) {

		log.info("modifyInfoById() start");
		if (errors.hasErrors()) {
			log.info("modifyInfoById() error");
			log.info("modifyInfoById() userDTO: " + userDTO);
			return "NO"; // 유효성 검사 오류가 발생하면 다시 회원가입 폼으로 이동
		}

		userService.modifyInfo(userDTO);
		log.info("modifyInfoById() end");

		return "YES";
	}

	// ################## MemberDelete ##################

	// 회원 정탈퇴 페이지 화면단
	@GetMapping("/mypage/memberDelete")
	public String toDeletePage(Principal principal, Model model) { // 회원정보 수정 페이지

		System.out.println("회원정보 수정 페이지");
		String id = principal.getName();
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute("user", userDTO);
		System.out.println(model.getAttribute("user"));
		return "/mypage/memberDelete";

	}

	// 회원탈퇴
	@PostMapping("/delete")
	@ResponseBody
	public String withdraw(HttpSession session, UserDTO userDTO, Principal principal) { // 탈퇴

		System.out.println("탈퇴할 계정 세션을 가져옴");
		String id = principal.getName();
		System.out.println(id);
		if (id != null) {
			userService.withdraw(id);
			System.out.println("회원 탈퇴");
			session.invalidate();
			System.out.println("세션삭제완료_login창 리턴");
			return "YES";
		}
		return "NO";
	}

	// ################## Password Check ##################

	// 회원수정시 비밀번호 체크 화면단
	@GetMapping("/mypage/memberUpdateCheck")
	public String memberUpdateCheck(Principal principal, Model model) {

		String id = principal.getName();
		if (id != null) {
			return "/mypage/memberUpdateCheck";
		}
		return "redirect:/app/login";
	}

	// 회원 탈퇴시 비밀번호 체크 화면단
	@GetMapping("/mypage/memberDeleteCheck")
	public String memberDeleteCheck(Principal principal, Model model) {

		String id = principal.getName();
		if (id != null) {
			return "/mypage/memberDeleteCheck";
		}
		return "redirect:/app/login";
	}
	
	// 회원 수정/탈퇴시 비밀번호 중복 체크
		@ResponseBody
		@PostMapping("/pwdChk")
		public String getpwd(String pwd, Principal principal) {
			
			String id = principal.getName();
			String EncordPwd = userService.getEncordpwd(id);
			
			boolean pwdCheck = passwordEncoder.matches(pwd, EncordPwd);
			
			if (pwdCheck) {
				System.out.println("OK");
				return "OK";
			}
			return "NO";
		}

	// ################## MemberPasswordUpdate ##################

	// 비밀번호 변경 화면단
	@GetMapping("/mypage/memberPasswordUpdate")
	public String memberUpdateDisplay(Principal principal, Model model) {

		String id = principal.getName();
		if (id != null) {
			return "/mypage/memberPasswordUpdate";
		}
		return "redirect:/app/login";
	}
	
	// 비밀번호 변경 화면단
		@PostMapping("/mypage/memberPasswordUpdate")
		@ResponseBody
		public String memberPasswordUpdate(String pwd, String changepwd, String rechangepwd, Principal principal, Model model) {

			System.out.println("pwd: "+pwd);
			System.out.println("changepwd: "+changepwd);
			System.out.println("rechangepwd: "+rechangepwd);

			String id = principal.getName();
			String EncordPwd = userService.getEncordpwd(id);
			boolean pwdCheck = passwordEncoder.matches(pwd, EncordPwd);
			System.out.println("pwdCheck: "+pwdCheck);
			
			if (pwdCheck) {
				System.out.println("현재 비번 OK");
				
				if(changepwd.equals(rechangepwd)) {
					System.out.println("변경할 비밀번호 일치 완료");
					userService.updatePwd(id, changepwd);
					
					return "OK";
				}else {
					System.out.println("비밀번호 확인 오류");
					return "NO";
				}
			}
			return "ANO";
		}
	
	
}











