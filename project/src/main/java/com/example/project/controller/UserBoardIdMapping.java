package com.example.project.controller;

import org.springframework.security.core.Authentication;

import com.example.project.config.auth.PrincipalUser;

public class UserBoardIdMapping {

	public int boardIdMapping(String userEO) {
		
	int boardId = 21;
	// 스위치문으로 boardid에 맞는 시도교육청 코드 매핑
	switch (userEO != null ? userEO: "NULL") {
		case "B10": boardId = 21;
		break;
		case "C10": boardId = 22;
		break;
		case "D10": boardId = 23;
		break;
		case "E10": boardId = 24;
		break;
		case "F10": boardId = 25;
		break;
		case "G10": boardId = 26;
		break;
		case "H10": boardId = 27;
		break;
		case "I10": boardId = 28;
		break;
		case "J10": boardId = 29;
		break;
		case "K10": boardId = 30;
		break;
		case "M10": boardId = 31;
		break;
		case "N10": boardId = 32;
		break;
		case "P10": boardId = 33;
		break;
		case "Q10": boardId = 34;
		break;
		case "R10": boardId = 35;
		break;
		case "S10": boardId = 36;
		break;
		case "T10": boardId = 37;
		break;
		default : boardId = 21;
	}
	return boardId;
	}
}
