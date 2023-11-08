package com.example.project.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.dto.UserDTO;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.LikeDto;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.service.MyPostService;
import com.example.project.service.NoticeService;
import com.example.project.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MyPostDetailController {

	final MyPostService mynoticeService;
	final NoticeService noticeService;
	final UserService userService;
	
	//################################## 게시글 자세히 보기 ##################################
	
	// 내가쓴 게시글 자세히 보기 페이지로 이동
	@GetMapping("/mypage/MyPostDetail")
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
		
		return "mypage/MyPostDetail"; // 내가쓴 게시글 자세히 보기 페이지로 이동
	}


	//################################## 게시글 수정 폼 ##################################
	 
	// 게시글 수정 폼 페이지로 이동
	@GetMapping("/mypage/MyPostEdit")
	public String noticeEditForm(Principal principal, final Model model, @RequestParam("postId") Long postId) {
	    if (principal == null) {
	        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }

	    // 게시글 수정을 위해 해당 게시글 정보를 불러와 모델에 추가
	    model.addAttribute("notice", noticeService.selectByPostId(postId));
	    return "mypage/MyPostEdit"; // 게시글 수정 폼 페이지로 이동
	}
	

	// 게시글 수정 처리
	@PostMapping("/mypage/MyPostEdit")
	public String noticeUpdate(Principal principal, @RequestParam(value = "postId", required = false) Long postId, @RequestParam String title, @RequestParam String content) {
	    if (principal == null) {
	        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }

	    // 입력된 게시글 정보를 이용하여 게시글을 수정
	    NoticeDto noticeDto = new NoticeDto(postId, title, content, null, null, null, null);
	    noticeService.updateNotice(noticeDto);
	    
	    // 수정된 게시글의 상세 페이지로 리다이렉트
	    return "redirect:/mypage/MyPostDetail?postId=" + noticeDto.getPostId();
	}
	
	// 게시글 삭제 처리
	@GetMapping("/mypage/MyPostDelete")
	public String noticeDelete(Long postId, @RequestParam(defaultValue = "1") int categoryId) {
	    // 게시글 삭제
	    noticeService.deleteNotice(postId);
	    
	    // 게시글 목록 페이지로 리다이렉트
	    return "redirect:/mypage/myPostList?categoryId=" + categoryId;
	}
	
	
	//################################## 좋아요 폼 ##################################

	// 게시글 좋아요 버튼 처리
	@GetMapping("/mypage/MyPostlike")
	public String likeBtn(Principal principal, @RequestParam(required = false) String memberId,
			@RequestParam(required = false) Long postId) {
		// 현재 로그인한 사용자의 ID를 가져옴
		memberId = principal.getName();
		LikeDto likeDto = new LikeDto(memberId, postId, null);

		// 게시글에 대한 좋아요 여부를 확인하고 처리
		if (noticeService.likeCheck(likeDto) == null || noticeService.likeCheck(likeDto).getGood() == false) {
			// 좋아요를 누르지 않은 경우, 좋아요 추가
			noticeService.addLike(likeDto);
		} else {
			// 이미 좋아요를 누른 경우, 좋아요 삭제
			noticeService.deleteLike(likeDto);
		}

		// 게시글 자세히 보기 페이지로 리다이렉트
		return "redirect:/mypage/MyPostDetail?postId=" + postId;
	}
	
	//################################## 댓글 폼 ##################################

	// 댓글 추가 처리
	@PostMapping("/mypage/MyPostAddCmt")
	public String addComment(Principal principal, @RequestParam Long postId, @RequestParam String comment, @RequestParam(required = false) Long parentCommentId) {
	    if (principal == null) {
	        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }

	    // 입력된 댓글 정보를 이용하여 댓글을 추가
	    CommentDto commentDto = new CommentDto(principal.getName(), comment, postId, parentCommentId);
	    noticeService.addComment(commentDto);
	    
	    // 게시글 자세히 보기 페이지로 리다이렉트
	    return "redirect:/mypage/MyPostDetail?postId=" + postId;
	}
	
	// 댓글 수정 처리
		@PostMapping("/mypage/MyPostEditCmt")
		public String editComment(Principal principal, @RequestParam Long postId, @RequestParam Long commentId, @RequestParam String comment, @RequestParam String memberId) {
		    if (principal == null) {
		        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
		        return "redirect:/login";
		    }
		    if (principal.getName().equals(memberId)) {
		        CommentDto commentDto = new CommentDto(null, comment, null, null, commentId);
		        noticeService.editComment(commentDto);
		    }
		    return "redirect:/mypage/MyPostDetail?postId=" + postId;
		}
	
	// 댓글 삭제 처리
	@GetMapping("/mypage/MyPostDelCmt")
	public String delComment(Principal principal, @RequestParam Long postId, @RequestParam Long commentId, @RequestParam String memberId) {
	    if (principal == null) {
	        // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }
	    if (principal.getName().equals(memberId)) {
	        if (noticeService.deleteCheck(commentId) != 0) {
	            System.out.println(noticeService.deleteCheck(commentId));
	            noticeService.deleteComment(commentId);
	        } else {
	            noticeService.completeDelComment(commentId);
	        }
	    }
	    return "redirect:/mypage/MyPostDetail?postId=" + postId;
	}

	//###########################################################################
	//###########################################################################
	//###########################################################################

	/*

	// 사용자의 소속 기관에 따른 공지사항 카테고리 목록을 반환
	@ResponseBody
	@GetMapping("/getCategories")
	public List<NoticeCategoryDto> getCategories(Principal principal) {
	    String userId = null;
	    String userEO = null;
	    
	    // 사용자가 로그인한 경우, 사용자 정보를 통해 소속 기관 코드 및 ID 가져오기
	    if (principal != null) {
	        userId = principal.getName();
	        userEO = userService.userInfo(userId).getOfficeOfEducationCode().toString();
	    }
	    
	    int boardId = 21; // 기본 게시판 ID
	    // 사용자의 소속 기관 코드에 따라 게시판 ID를 설정
	    switch (userEO != null ? userEO : "NULL") {
	        case "B10": boardId = 21;
	            break;
	        case "C10": boardId = 22;
	            break;
	        case "D10": boardId = 23;
	            break;
	        // (중략) 다른 소속 기관 코드에 따른 게시판 ID 설정
	        default: boardId = 21; // 기본 게시판 ID
	    }
	    
	    // 설정된 게시판 ID에 해당하는 공지사항 카테고리 목록을 반환
	    return noticeService.selectUserNoticeCategoryList(boardId);
	}

	// 모든 공지사항 카테고리 목록을 반환
	@ResponseBody
	@GetMapping("/getCustomCategories")
	public List<NoticeCategoryDto> getCustomCategories() {
	    // 모든 공지사항 카테고리 목록을 반환
	    return noticeService.selectNoticeCategoryList();
	}

	// 게시판 목록을 반환
	@ResponseBody
	@GetMapping("/getBoard")
	public List<BoardDto> getBoard() {
	    // 모든 게시판 목록을 반환
	    return noticeService.selectBoardList();
	}
	*/
	
	
}
