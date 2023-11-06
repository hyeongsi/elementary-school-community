package com.example.project.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.project.dto.board.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.LikeDto;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.service.NoticeService;
import com.example.project.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserNoticeController {

	final NoticeService noticeService;
	final UserService userService;
	
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
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
		model.addAttribute("categoryId", categoryId);
		return "notice/noticeWrite";
	}
	
	
	
	@PostMapping("/notice/write")
	public String noticeWrite(HttpSession session,
							  @RequestParam String title,
							  @RequestParam String content, 
							  @RequestParam Long categoryId){
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
		NoticeDto noticeDto = new NoticeDto(null , title, content,null,null, session.getAttribute("userId").toString(),categoryId);
		noticeService.insertNotice(noticeDto);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/notice/edit")
	public String noticeEditForm(HttpSession session,final Model model,
								@RequestParam("postId") Long postId) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
		// NoticeDto noticeDto = new NoticeDto(null, title, content,null,null,null);
		model.addAttribute("notice", noticeService.selectByPostId(postId));
		return "notice/edit";
	}

	
	@PostMapping("/notice/edit")
	public String noticeUpdate(HttpSession session,
							   @RequestParam(value="postId", required=false) Long postId, 
							   @RequestParam String title, 
							   @RequestParam String content) {
		
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
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
		System.out.println(session.getAttribute("userId"));
		noticeService.updateViewCnt(postId);
		NoticeDto noticeDto = noticeService.selectByPostId(postId);
		model.addAttribute("notice", noticeDto);
		model.addAttribute("Comment",noticeService.selectCommentList(postId));
		model.addAttribute("like", noticeService.likeCnt(postId));
		
		return "notice/detail";
	}
	

	@PostMapping("/addComment")
	public String addComment(HttpSession session,
							 @RequestParam Long postId,
							 @RequestParam String comment,
							 @RequestParam(required = false) Long parentCommentId) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
		
		
		CommentDto commentDto = new CommentDto(session.getAttribute("userId").toString(), comment, postId, parentCommentId);
		noticeService.addComment(commentDto);
		
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@GetMapping("/delComment")
	public String delComment(HttpSession session,
			 				@RequestParam Long postId,
			 				@RequestParam Long commentId,
			 				@RequestParam String memberId) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
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
		
		if(session.getAttribute("userId")==null) {
			return "redirect:/login";
		}
		if(session.getAttribute("userId").toString().equals(memberId)) {
			CommentDto commentDto = new CommentDto(null, comment, null, null, commentId);
			noticeService.editComment(commentDto);
			}
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@GetMapping("/notice/like")
	public String likeBtn(HttpSession session,
						  @RequestParam(required = false) String memberId,
					      @RequestParam(required = false) Long postId) {
		memberId = session.getAttribute("userId").toString();
		LikeDto likeDto = new LikeDto(memberId, postId, null);
		if(noticeService.likeCheck(likeDto)==null||noticeService.likeCheck(likeDto).getGood()==false) {
			if(noticeService.likeCheck(likeDto)==null) {
				noticeService.addLike(likeDto);
			} else {
				noticeService.reLike(likeDto);
			}
		}else {
			noticeService.cancelLike(likeDto);
		}
		return "redirect:/notice/detail?postId="+postId;
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
    public List<NoticeCategoryDto> getCategories(HttpSession session) {
		String userId = null;
		String userEO = null;
		if(session.getAttribute("userId")!=null) {
		userId = session.getAttribute("userId").toString();	// 시도교육청
		userEO = userService.userInfo(userId).getOfficeOfEducationCode().toString();
		}
		int boardId = 21;
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
		System.out.println(boardId);
        return noticeService.selectUserNoticeCategoryList(boardId);
    }

	@ResponseBody
	@GetMapping("/getCustomCategories")
	public List<NoticeCategoryDto> getCustomCategories(HttpSession session) {

		return noticeService.selectNoticeCategoryList();
	}

	@ResponseBody
	@GetMapping("/getBoard")
	public List<BoardDto> getBoard() {

		return noticeService.selectBoardList();
	}
	
}
