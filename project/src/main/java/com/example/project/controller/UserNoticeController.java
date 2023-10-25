package com.example.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.service.NoticeService;

@Controller
public class UserNoticeController {

	final NoticeService noticeService;

	public UserNoticeController(final NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/notice/list")
	public String noticeList(final Model model,
            				@RequestParam(defaultValue = "10") int displayUnit,
            				@RequestParam(defaultValue = "1") int curPage,@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="title") String searchType) {
		
			final NoticePageDto noticePageDto = noticeService.selectSearchNoticePage(displayUnit, curPage, keyword, searchType);
			model.addAttribute("noticePageDto", noticePageDto);
		return "notice/noticeList";
	}
	
	@GetMapping("/notice/write")
	public String noticeWriteForm() {
		
		return "notice/noticeWrite";
	}
	
	
	
	@PostMapping("/notice/write")
	public String noticeWrite(@RequestParam String title,@RequestParam String content){
		NoticeDto noticeDto = new NoticeDto(null , title, content,null,null,null);
		System.out.println(noticeDto);
		noticeService.insertNotice(noticeDto);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/notice/edit")
	public String noticeEditForm(final Model model,  @RequestParam("postId") Long postId) {
		// NoticeDto noticeDto = new NoticeDto(null, title, content,null,null,null);
		model.addAttribute("notice", noticeService.selectByPostId(postId));
		return "notice/edit";
	}

	
	@PostMapping("/notice/edit")
	public String noticeUpdate(@RequestParam(value="postId", required=false) Long postId, @RequestParam String title, @RequestParam String content) {
		System.out.println(postId);
		NoticeDto noticeDto = new NoticeDto(postId, title, content, null, null, null);
		System.out.println(noticeDto.getPostId());
		noticeService.updateNotice(noticeDto);
		return "redirect:/notice/detail"+"?postId="+noticeDto.getPostId();
	}
	
	@GetMapping("/notice/delete")
	public String noticeDelete(Long postId) {
		System.out.println(postId);
		noticeService.deleteNotice(postId);
		
		return "redirect:/notice/list";
	}
	
	@GetMapping("/notice/detail")
	public String retrieve(final Model model, @RequestParam Long postId) {
		
		noticeService.updateViewCnt(postId);
		model.addAttribute("notice", noticeService.selectByPostId(postId));
		return "notice/detail";
	}
}
