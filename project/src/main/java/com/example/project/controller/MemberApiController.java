package com.example.project.controller;

import com.example.project.dto.SchoolInfo;
import com.example.project.dto.UserDTO;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final UserService userService;
 
    @GetMapping("/member/schoolInfo")
    public SchoolInfo schoolInfoDetails(HttpSession session){
    	
        session.getAttribute("userId");

        System.out.println(session.getAttribute("userId"));
        SchoolInfo schoolInfo = userService.userInfo(session.getAttribute("userId").toString());
        System.out.println(schoolInfo.getOfficeOfEducationCode());
        return schoolInfo;
    }
    
    
}