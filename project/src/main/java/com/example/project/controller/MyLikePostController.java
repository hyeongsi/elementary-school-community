package com.example.project.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.dto.notice.NoticePageDto;
import com.example.project.service.MyLikePostService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MyLikePostController {

	final MyLikePostService mylikepostService;
	
	// 좋아요누른 게시글 화면단
	@GetMapping("/mypage/myLikesList")
    public String LikesList(Principal principal, Model model,
                            @RequestParam(defaultValue = "10") int displayUnit,
                            @RequestParam(defaultValue = "1") int curPage,
                            @RequestParam(defaultValue = "") String keyword,
                            @RequestParam(defaultValue = "title") String searchType,
                            @RequestParam(defaultValue = "1") int categoryId,
                            HttpSession session) {
		
        if (principal != null) {
            String userId = principal.getName();
            // 사용자 ID를 세션에 저장
            session.setAttribute("userId", userId);
        } else {
            // 로그인하지 않은 경우 처리
            // 이 부분을 로그인 페이지로 리다이렉트 또는 로그인 요청으로 변경해야 할 수 있습니다.
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 세션에서 사용자 ID 가져오기
        String memberId = (String) session.getAttribute("userId");

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        final NoticePageDto noticePageDto = mylikepostService.selectSearchLikePostById(displayUnit, curPage, keyword, searchType, categoryId, memberId);

        model.addAttribute("noticePageDto", noticePageDto);
        System.out.println(noticePageDto.getNoticeDtoList());

        return "mypage/myLikesList";
    }
}
