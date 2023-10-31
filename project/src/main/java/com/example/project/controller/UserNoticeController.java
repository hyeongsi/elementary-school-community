package com.example.project.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserNoticeController {

	final NoticeService noticeService;

	@GetMapping("/notice/list")
	public String noticeList(final Model model,HttpSession session, Principal principal,
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
	public String noticeWriteForm(final Model model,HttpSession session, @RequestParam Long categoryId) {
		
		model.addAttribute("categoryId", categoryId);
		return "notice/noticeWrite";
	}
	
	
	
	@PostMapping("/notice/write")
	public String noticeWrite(HttpSession session,
							  @RequestParam String title,
							  @RequestParam String content, 
							  @RequestParam Long categoryId){
		
		NoticeDto noticeDto = new NoticeDto(null , title, content,null,null, session.getAttribute("userId").toString(),categoryId);
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
		NoticeDto noticeDto = new NoticeDto(postId, title, content, null, null, null,null);
		noticeService.updateNotice(noticeDto);
		return "redirect:/notice/detail?postId="+noticeDto.getPostId();
	}
	
	@GetMapping("/notice/delete")
	public String noticeDelete(Long postId,
								@RequestParam(defaultValue = "1") int categoryId) {
		noticeService.deleteNotice(postId);
		return "redirect:/notice/list?categoryId="+categoryId;
	}
	
	@GetMapping("/notice/detail")
	public String retrieve(HttpSession session,final Model model, @RequestParam Long postId,
							@RequestParam(value="categoryId", defaultValue = "1") int categoryId) {
		
		noticeService.updateViewCnt(postId);
		NoticeDto noticeDto = noticeService.selectByPostId(postId);
		model.addAttribute("notice", noticeDto);
		model.addAttribute("Comment",noticeService.selectCommentList(postId));
		
		final String compareId = noticeDto.getMemberId();
		if(session.getAttribute("userId").toString().equals(compareId)){
			session.setAttribute("eqId", 1);
		} else {
			session.setAttribute("eqId", 0);
		}
//		if(session.getAttribute("userId").toString().equals(model.getAttribute("notice.memberId"))){System.out.println("같음");
//			}
		
		return "notice/detail";
	}
	

	@PostMapping("/addComment")
	public String addComment(HttpSession session,
							 @RequestParam Long postId,
							 @RequestParam String comment,
							 @RequestParam(required = false) Long parentCommentId) {
		
		CommentDto commentDto = new CommentDto(session.getAttribute("userId").toString(), comment, postId, parentCommentId);
		noticeService.addComment(commentDto);
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@GetMapping("/delComment")
	public String delComment(HttpSession session,
			 				@RequestParam Long postId,
			 				@RequestParam Long commentId,
			 				@RequestParam String memberId) {
		if(session.getAttribute("userId").toString().equals(memberId)) {
		noticeService.deleteComment(commentId);
		}
		return "redirect:notice/detail?postId="+postId;
	}
	
	@PostMapping("/editComment")
	public String editComment(HttpSession session,
							  @RequestParam Long postId,
							  @RequestParam Long commentId,
							  @RequestParam String comment,
							  @RequestParam String memberId) {
		
		if(session.getAttribute("userId").toString().equals(memberId)) {
			CommentDto commentDto = new CommentDto(null, comment, null, null, commentId);
			noticeService.editComment(commentDto);
			}
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
    public List<NoticeCategoryDto> getCategories() {
        return noticeService.selectNoticeCategoryList();
    }
	
	
}
