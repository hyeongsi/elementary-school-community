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
	
	// 비밀번호찾기 화면단
	@GetMapping("/findPassword")
    public String findPassword() {
        return "findPassword";
    }
	

	//Email과 name의 일치여부를 check하는 컨트롤러
    @GetMapping("/findPw")
    public @ResponseBody Map<String, Boolean> pwd_find(String email, String id){
    	Map<String,Boolean> json = new HashMap<>();
    	System.out.println("GET");
        boolean pwFindCheck = fus.userEmailCheck(email,id);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }
    

   //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
   @PostMapping("/sendEmail")
   public @ResponseBody void sendEmail(String email, String id){
	   System.out.println("POST받아옴");
	   MailDTO mailDTO = ses.createMailAndChangePassword(email, id);
	   System.out.println("POST받아옴");
	   ses.mailSend(mailDTO);
    }     
}
