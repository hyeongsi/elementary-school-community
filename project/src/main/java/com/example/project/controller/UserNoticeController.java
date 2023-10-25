package com.example.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.project.dto.NoticeDto;
import com.example.project.dto.SearchDto;
import com.example.project.service.NoticeService;

@Controller
public class UserNoticeController {

	final NoticeService noticeService;

	public UserNoticeController(final NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/notice/list")
	public String noticeList(final Model model) {
		
		List<NoticeDto> noticeList = noticeService.selectNoticieList();
		System.out.println("notice테스트");
		model.addAttribute("noticeList",noticeList);
		System.out.println("notice테스트2");
		System.out.println(noticeList);
		System.out.println("notice테스트3");
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
	public String noticeEditForm(final Model model,  @RequestParam("postId") int postId) {
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
	public String retrieve(final Model model, @RequestParam("postId") int postId) {
		model.addAttribute("notice", noticeService.selectByPostId(postId));
		return "notice/detail";
	}
}
