package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.MailDTO;
import com.example.project.service.FindUserService;
import com.example.project.service.SendEmailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FindPasswordController {
	
	private final FindUserService fus;
	SendEmailService ses;
	
	// ################## FindPassword and Send Email ##################
	
	// 비밀번호 찾기 화면단
	@GetMapping("/findPassword")
    public String findPassword() {
        return "findPassword";
    }
	

	//입력받은 email과 id를 체크
    @GetMapping("/findPw")
    public @ResponseBody Map<String, Boolean> pwd_find(String email, String id){
    	
    	Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = fus.userEmailCheck(email,id);
        json.put("check", pwFindCheck);
        
        return json;
    }
    
 
   //사용자가 가입했던 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경
   @PostMapping("/sendEmail")
   public @ResponseBody void sendEmail(String email, String id){
	   
	   MailDTO mailDTO = ses.createMailAndChangePassword(email, id);
	   ses.mailSend(mailDTO);
	   
    }     
}
