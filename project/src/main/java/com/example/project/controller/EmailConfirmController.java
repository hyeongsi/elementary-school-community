package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.MailDTO;
import com.example.project.service.ConfirmEmailService;
import com.example.project.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EmailConfirmController {
	
	ConfirmEmailService ces;
	UserService userService;
	
	// ################## Confirm and Send Email ##################
	
	//인증번호 발송
	   @PostMapping("/ConfirmEmail")
	   public @ResponseBody String sendConfirmEmail(String email){
		   
		   boolean e = userService.getEmail(email);
		   
		   if(e) {
			   System.out.println("NO");
			   return "NO"; // 존재
		   }
		   
		   MailDTO mailDTO = ces.createConfirmMail(email);
		   ces.mailSend(mailDTO);
		   
		   System.out.println("OK");
		   return "OK";
	    }    
	

	//입력값과 인증번호 체크
    @GetMapping("/ConfirmCheck")
    public @ResponseBody Map<String, Boolean> CheckConfirmNumber(String InputNumber){
    	
    	System.out.println("Get");
    	Map<String,Boolean> json = new HashMap<>();
        boolean CheckNumber = ces.CheckConfirm(InputNumber);
        json.put("check", CheckNumber);
        
        System.out.println(json);
        
        return json;
    }
     
}
