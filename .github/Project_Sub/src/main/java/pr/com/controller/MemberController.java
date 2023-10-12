package pr.com.controller;

import java.lang.reflect.Member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pr.com.service.MemberService;
import pr.com.dto.MemberDTO;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 post
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public String postRegister(MemberDTO dto) throws Exception {
			logger.info("post register");
			int result = service.idChk(dto);
			try {
				if(result == 1) {
					return "/member/register";
				}else if(result == 0) {
					service.register(dto);
				}
				/* 여기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
				      존재하지 않는다면 -> register */
			} catch (Exception e) {
				throw new RuntimeException();
			}
			return "redirect:/";
		}
	
	// 로그인 post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		HttpSession session = req.getSession();
		MemberDTO login = service.login(dto);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 정보 수정
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "member/memberUpdateView";
	}
	
	// 회원 정보 수정 이후
	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberDTO dto, HttpSession session) throws Exception{
		
		service.memberUpdate(dto);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 탈퇴 get
		@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
		public String memberDeleteView() throws Exception{
			return "member/memberDeleteView";
		}
		
	// 회원 탈퇴 post
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception{
			
		// 세션에 있는 member를 가져와 member변수에 넣어준다.
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		// 세션에있는 비밀번호
		String sessionPass = member.getUserPass();
		// dto로 들어오는 비밀번호
		String voPass = dto.getUserPass();
			
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}
		service.memberDelete(dto);
		session.invalidate();
		return "redirect:/";
	}
	
	
	// 패스워드 체크
	@ResponseBody
	@RequestMapping(value="/passChk", method = RequestMethod.POST)
	public int passChk(MemberDTO dto) throws Exception {
		int result = service.passChk(dto);
		return result;
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberDTO dto) throws Exception {
		int result = service.idChk(dto);
		return result;
	}
		
}







