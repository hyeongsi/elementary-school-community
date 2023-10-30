package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project.dto.MailDTO;
import com.example.project.service.MailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {
	
	private final MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }
    
    @GetMapping("/findPasswordTset")
    public String display() {
        return "findPasswordTset";
    }

    @PostMapping("/mail")
    public void execMail(MailDTO mailDTO) {
        mailService.mailSend(mailDTO);
      
    }
}
