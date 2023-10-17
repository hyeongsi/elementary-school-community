package com.example.project.controller;

import com.example.project.dto.SchoolInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

    @GetMapping("/member/schoolInfo")
    public SchoolInfo schoolInfoDetails(){
        return new SchoolInfo("S10", 9091055);
    }
    
    
}