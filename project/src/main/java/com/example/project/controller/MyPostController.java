package com.example.project.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.service.MyPostService;
import com.example.project.service.NoticeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MyPostController {

	final MyPostService mynoticeService;
	final NoticeService noticeService;
	
	// 내가쓴 게시글 화면단
	@GetMapping("/mypage/myPostList")
	public String noticeList(Principal principal, Model model,
	                        @RequestParam(defaultValue = "10") int displayUnit,
	                        @RequestParam(defaultValue = "1") int curPage,
	                        @RequestParam(defaultValue = "") String keyword,
	                        @RequestParam(defaultValue = "title") String searchType,
	                        @RequestParam(defaultValue = "1") int categoryId,
	                        HttpSession session) {

	    // 사용자가 로그인한 상태인지 확인
	    if (principal != null) {
	        // 사용자가 로그인한 경우, 현재 로그인한 사용자의 이름(Principal)을 가져옴
	        String userId = principal.getName();
	        // 사용자 ID를 세션에 저장
	        session.setAttribute("userId", userId);
	    } else {
	        // 사용자가 로그인하지 않은 경우 처리
	        // 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }

	    // 세션에서 사용자 ID 가져오기
	    String memberId = (String) session.getAttribute("userId");

	    // 모델에 데이터 추가
	    model.addAttribute("categoryId", categoryId);
	    model.addAttribute("searchType", searchType);
	    model.addAttribute("keyword", keyword);

	    // 내가쓴 게시글 페이지 정보를 조회하기 위해 서비스의 메서드 호출
	    final NoticePageDto noticePageDto = mynoticeService.selectSearchNoticePageById(displayUnit, curPage, keyword, searchType, categoryId, memberId);

	    // 조회된 게시글 페이지 정보를 모델에 추가
	    model.addAttribute("noticePageDto", noticePageDto);

	    return "mypage/myPostList";
	}
}
