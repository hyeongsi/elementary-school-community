package com.example.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.notice.CommentDto;
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
            				@RequestParam(defaultValue = "1") int curPage,
            				@RequestParam(defaultValue = "") String keyword,
            				@RequestParam(defaultValue = "title") String searchType,
            				@RequestParam(defaultValue = "1") int categoryId) {
		
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		final NoticePageDto noticePageDto = noticeService.selectSearchNoticePage(displayUnit, curPage, keyword, searchType, categoryId);
		
		model.addAttribute("noticePageDto", noticePageDto);
		return "notice/noticeList";
	}
	
	@GetMapping("/notice/write")
	public String noticeWriteForm() {
		
		return "notice/noticeWrite";
	}
	
	
	
	@PostMapping("/notice/write")
	public String noticeWrite(@RequestParam String title,@RequestParam String content, @RequestParam Long categoryId){
		NoticeDto noticeDto = new NoticeDto(null , title, content,null,null,null,null);
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
	public String noticeUpdate(@RequestParam(value="postId", required=false) Long postId, 
							   @RequestParam String title, 
							   @RequestParam String content) {
		System.out.println(postId);
		NoticeDto noticeDto = new NoticeDto(postId, title, content, null, null, null,null);
		System.out.println(noticeDto.getPostId());
		noticeService.updateNotice(noticeDto);
		return "redirect:/notice/detail"+"?postId="+noticeDto.getPostId();
	}
	
	@GetMapping("/notice/delete")
	public String noticeDelete(Long postId,
								@RequestParam(defaultValue = "1") int categoryId) {
		System.out.println(postId);
		noticeService.deleteNotice(postId);
		System.out.println(categoryId);
		return "redirect:/notice/list"+"?categoryId="+categoryId;
	}
	
	@GetMapping("/notice/detail")
	public String retrieve(final Model model, @RequestParam Long postId,
							@RequestParam(value="categoryId", defaultValue = "1") int categoryId) {
		
		
		System.out.println(categoryId);
		noticeService.updateViewCnt(postId);
		model.addAttribute("notice", noticeService.selectByPostId(postId));
		//model.addAttribute("comment", noticeService.selectCommentList(postId));
		System.out.println(noticeService.selectCommentList(postId));
		model.addAttribute("Comment",noticeService.selectCommentList(postId));
		return "notice/detail";
	}
	
//	@GetMapping("addComment")
//	public String addCommentForm(@RequestParam int postId) {
//		System.out.println("댓글작성GET");
//		return "redirect:notice/detail?postId="+postId;
//	}
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam Long postId,
							 @RequestParam String comment) {
		CommentDto commentDto = new CommentDto(new Long(9999), comment, postId);
		System.out.println("댓글작성POST");
		System.out.println(commentDto.toString());
		noticeService.addComment(commentDto);
		System.out.println("댓글작성POST완료");
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
    public List<NoticeCategoryDto> getCategories() {
        return noticeService.selectNoticeCategoryList();
    }
	
	
}
