package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CafeteriaController {

	@GetMapping("/cafeteria")
	public String cafeteri() {
		
		return "/cafeteria";
	}
}
