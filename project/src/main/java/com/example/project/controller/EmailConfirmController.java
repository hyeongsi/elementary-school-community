package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.MailDTO;
import com.example.project.service.ConfirmEmailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EmailConfirmController {
	
	ConfirmEmailService ces;
	
	// ################## Confirm and Send Email ##################
	
	//인증번호 발송
	   @PostMapping("/ConfirmEmail")
	   public @ResponseBody void sendConfirmEmail(String email){
		   
		   MailDTO mailDTO = ces.createConfirmMail(email);
		   ces.mailSend(mailDTO);
		   
	    }    
	

	//입력값과 인증번호 체크
    @GetMapping("/ConfirmCheck")
    public @ResponseBody Map<String, Boolean> CheckConfirmNumber(String InputNumber){
    	
    	Map<String,Boolean> json = new HashMap<>();
        boolean CheckNumber = ces.CheckConfirm(InputNumber);
        json.put("check", CheckNumber);
        
        return json;
    }
     
}
