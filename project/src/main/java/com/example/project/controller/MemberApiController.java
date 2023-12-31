package com.example.project.controller;

import com.example.project.dto.SchoolInfo;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final UserService userService;
 
    @GetMapping("/member/schoolInfo")
    public SchoolInfo schoolInfoDetails (Principal principal) {

        if(principal == null)
            return null;

        SchoolInfo schoolInfo = userService.userInfo(principal.getName());
        return schoolInfo;
    }
    
    
}