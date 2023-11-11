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
import com.example.project.dto.UserDTO;
import com.example.project.dto.board.BoardDto;
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
	
	// 게시판 목록
	@GetMapping("/notice/list")
	public String noticeList(final Model model,HttpSession session, Principal principal,
            				@RequestParam(defaultValue = "10") int displayUnit,
            				@RequestParam(defaultValue = "1") int curPage,
            				@RequestParam(defaultValue = "") String keyword,
            				@RequestParam(defaultValue = "title") String searchType,
            				@RequestParam(defaultValue = "1") int categoryId,
            				@RequestParam(required = false) String WriteTime) {
		
		String memberId = (String) session.getAttribute("userId");
	    
	    UserDTO userDTO = userService.getUserById(memberId);
		model.addAttribute("User", userDTO);
		
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		final NoticePageDto noticePageDto = noticeService.selectSearchNoticePage(displayUnit, curPage, keyword, searchType, categoryId, WriteTime);
		model.addAttribute("noticePageDto", noticePageDto);
		return "notice/noticeList";
	}
	
	// 게시글쓰기
	@GetMapping("/notice/write")
	public String noticeWriteForm(HttpSession session, final Model model, Principal principal, @RequestParam Long categoryId) {
		if(principal == null) {
			return "redirect:/login";
		}
		
		String memberId = (String) session.getAttribute("userId");
	    
	    UserDTO userDTO = userService.getUserById(memberId);
		model.addAttribute("User", userDTO);
		model.addAttribute("categoryId", categoryId);
		return "notice/noticeWrite";
	}
	
	@PostMapping("/notice/write")
	public String noticeWrite(Principal principal,
							  @RequestParam String title,
							  @RequestParam String content, 
							  @RequestParam Long categoryId){
		if(principal == null) {
			return "redirect:/login";
		}

		NoticeDto noticeDto = new NoticeDto(null , title, content,null,null, principal.getName(),categoryId);
		noticeService.insertNotice(noticeDto);
		return "redirect:/notice/list?categoryId="+categoryId;
	}
	
	
	
	// 게시글 수정 폼 페이지로 이동
		@GetMapping("/notice/edit")
		public String noticeEditForm(HttpSession session, Principal principal, final Model model, @RequestParam("postId") Long postId) {
		    
			if (principal == null) {
		        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
		        return "redirect:/login";
		    }
			
			// 세션에서 받아온 ID값으로 유저 이름 호출
			String id = (String) session.getAttribute("userId");
			UserDTO userDTO = userService.getUserById(id);
			model.addAttribute("User", userDTO);
					
		    // 게시글 수정을 위해 해당 게시글 정보를 불러와 모델에 추가
		    model.addAttribute("notice", noticeService.selectByPostId(postId));
		    return "notice/edit"; // 게시글 수정 폼 페이지로 이동
		}
		
		

		// 게시글 수정 처리
		@ResponseBody
		@PostMapping("/notice/edit")
		public String noticeUpdate(Principal principal, @RequestParam(value = "postId", required = false) Long postId, @RequestParam String title, @RequestParam String content) {
		    if (principal == null) {
		        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
		        return "redirect:/login";
		    }
		    
		    System.out.println("엄준식");

		    // 입력된 게시글 정보를 이용하여 게시글을 수정
		    NoticeDto noticeDto = new NoticeDto(postId, title, content, null, null, null, null);
		    noticeService.updateNotice(noticeDto);
		    
		    // 수정된 게시글의 상세 페이지로 리다이렉트
		return "YES";
	}
	
	// 게시글 삭제
	@GetMapping("/notice/delete")
	@ResponseBody
	public String noticeDelete(Long postId,
								@RequestParam(defaultValue = "1") int categoryId) {
		noticeService.deleteNotice(postId);
		return "YES";
	}
	
	
	// 내가쓴 게시글 자세히 보기 페이지로 이동
		@GetMapping("/notice/detail")
		public String retrieve(HttpSession session, final Model model, @RequestParam Long postId,
				@RequestParam(value = "categoryId", defaultValue = "1") int categoryId) {
			// 사용자의 세션 정보에서 userId를 가져와서 출력
			System.out.println(session.getAttribute("userId"));

			String id = (String) session.getAttribute("userId");
			
			// 게시글 조회수 증가
			noticeService.updateViewCnt(postId);

			// 게시글 정보, 댓글 목록, 좋아요 수를 모델에 추가
			NoticeDto noticeDto = noticeService.selectByPostId(postId);
			UserDTO userDTO = userService.getUserById(id);
			
			model.addAttribute("notice", noticeDto);
			
			// 댓글 정보 가져옴
			model.addAttribute("Comment", noticeService.selectCommentList(postId));
			
			model.addAttribute("like", noticeService.likeCnt(postId));
			model.addAttribute("User", userDTO);
			
			return "notice/detail"; // 내가쓴 게시글 자세히 보기 페이지로 이동
		}
	
	// 댓글추가
	@PostMapping("/addComment")
	public String addComment(Principal principal,
							 @RequestParam Long postId,
							 @RequestParam String comment,
							 @RequestParam(required = false) Long parentCommentId) {
		if(principal==null) {
			return "redirect:/login";
		}

		CommentDto commentDto = new CommentDto(principal.getName(), comment, postId, parentCommentId);
		noticeService.addComment(commentDto);
		
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@GetMapping("/delComment")
	public String delComment(Principal principal,
			 				@RequestParam Long postId,
			 				@RequestParam Long commentId,
			 				@RequestParam String memberId) {
		
		
		if(principal == null) {
			return "redirect:/login";
		}
		if(principal.getName().equals(memberId)) {
			if(noticeService.deleteCheck(commentId)!=0) {
				System.out.println(noticeService.deleteCheck(commentId));
			noticeService.deleteComment(commentId);
			}else {
				noticeService.completeDelComment(commentId);
			}
		}
		return "redirect:notice/detail?postId="+postId;
	}
	
	@PostMapping("/editComment")
	public String editComment(Principal principal,
							  @RequestParam Long postId,
							  @RequestParam Long commentId,
							  @RequestParam String comment,
							  @RequestParam String memberId) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		if(principal.getName().equals(memberId)) {
			CommentDto commentDto = new CommentDto(null, comment, null, null, commentId);
			noticeService.editComment(commentDto);
			}
		
		return "redirect:notice/detail?postId="+postId;
	}
	
	@GetMapping("/notice/like")
	public String likeBtn(Principal principal,
						  @RequestParam(required = false) String memberId,
					      @RequestParam(required = false) Long postId) {
		memberId = principal.getName();
		LikeDto likeDto = new LikeDto(memberId, postId, null);
		if(noticeService.likeCheck(likeDto)==null||noticeService.likeCheck(likeDto).getGood()==false) {
			noticeService.addLike(likeDto);
			} else {
			noticeService.deleteLike(likeDto);
		}
		return "redirect:/notice/detail?postId="+postId;
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
    public List<NoticeCategoryDto> getCategories(Principal principal) {
		String userId = null;
		String userEO = null;
		if(principal != null) {
		userId = principal.getName();
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
		return noticeService.selectUserNoticeCategoryList(boardId);
    }	
	
	@ResponseBody
	@GetMapping("/getCustomCategories")
    public List<NoticeCategoryDto> getCustomCategories() {
		
        return noticeService.selectNoticeCategoryList();
    }
	
	@ResponseBody
	@GetMapping("/getBoard")
    public List<BoardDto> getBoard() {
		
        return noticeService.selectBoardList();
    }
	
}
