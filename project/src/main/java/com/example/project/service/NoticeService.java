package com.example.project.service;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.board.BoardDto;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.LikeDto;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;

import org.springframework.stereotype.Service;
import com.example.project.mapper.NoticeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    final NoticeMapper noticeMapper;

    public NoticeService(final NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    
    /*
    public NoticePageDto selectNoticePage(final int displayUnit, final int curPage) {
    	final int totalCnt = noticeMapper.noticeTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<NoticeDto> noticeDtoList = noticeMapper.selectNoticePage(pageDto);

        final NoticePageDto noticePageDto = new NoticePageDto(page, noticeDtoList);
        return noticePageDto;
    	
    }
    */
    
    // 페이징과 검색기능을 포함하여 list를 불러옴
    public NoticePageDto selectSearchNoticePage(final int displayUnit, final int curPage, String keyword, String searchtype,int categoryId) {
    	
    	final Map<String, String> map = new HashMap<>();
    	map.put("searchType", searchtype);
    	map.put("keyword", keyword);
    	map.put("categoryId", Integer.toString(categoryId));
    	final int totalCnt = noticeMapper.noticeTotalCnt(map);
    	final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum(), keyword, searchtype, categoryId);
     
        final List<NoticeDto> noticeDtoList = noticeMapper.selectSearchNoticePage(pageDto);
  

        final NoticePageDto noticePageDto = new NoticePageDto(page, noticeDtoList);
        return noticePageDto;
    	
        
    } 
    
    // 페이징과 검색 없이 전체 리스트를 가져옴
    // 현재는 안쓰임
    public List<NoticeDto> selectNoticieList() {
        return noticeMapper.selectNoticeList();
    }
    
    // 조회수 카운트
    public int updateViewCnt(Long postId) {
    	
    	return noticeMapper.updateViewCnt(postId);
    }
    
    // 글 작성
    public int insertNotice(final NoticeDto noticeDto) {
    	return noticeMapper.insertNotice(noticeDto);
    }
    
    // 글 삭제
    public int deleteNotice(Long postId) {
    	return noticeMapper.deleteNotice(postId);
    }
    
    // 게시글 상세보기를 위한 해당 postid에 대한 내용만 가져옴
    public NoticeDto selectByPostId(Long postId) {
    	return noticeMapper.selectByPostId(postId);
    }
    
    // 게시글 수정
    public int updateNotice(final NoticeDto noticeDto) {
    	return noticeMapper.updateNotice(noticeDto);
    }

    // 게시글 카테고리 리스트
    public List<NoticeCategoryDto> selectNoticeCategoryList() {
    	return noticeMapper.selectNoticeCategoryList();
    }
    
    // 커스텀 게시글 카테고리 리스트
    public List<NoticeCategoryDto> selectUserNoticeCategoryList(int boardId) {
    	return noticeMapper.selectUserNoticeCategoryList(boardId);
    }
    
    // 댓글추가
    public void addComment(CommentDto commnetDto) {
    	noticeMapper.addComment(commnetDto);
    }
    
    // 해당 게시글의 댓글 목록 list
    public List<CommentDto> selectCommentList(Long postId){
    	return noticeMapper.selectCommentList(postId);
    }
    
    // 댓글 삭제(자식 댓글이 있을 경우 수정으로 눈속임)
    public void deleteComment(Long commentId) {
    	noticeMapper.deleteComment(commentId);
    }
    
    // 댓글 삭제상태 체크
    public int deleteCheck(Long commentId) {
    	return noticeMapper.deleteCheck(commentId);
    }
    
    // 댓글 완전 삭제(자식 댓글이 없을 경우)
    public void completeDelComment(Long commentId) {
    	noticeMapper.completeDelComment(commentId);
    }
    
    // 댓글 수정
    public void editComment(CommentDto commentDto) {
    	noticeMapper.editComment(commentDto);
    }
    
    
    // 좋아요 상태(로그) 체크
    public LikeDto likeCheck(LikeDto likeDto) {
    	return noticeMapper.likeCheck(likeDto);
    }
    
    // 좋아요 로그 남기기
    public void addLike(LikeDto likeDto) {
    	noticeMapper.addLike(likeDto);
    }
    
    // 좋아요 취소(좋아요는 취소하되 로그는 남음)
    public void cancelLike(LikeDto likeDto) {
    	noticeMapper.cancelLike(likeDto);
    }
    
    // 좋아요 취소(좋아요 기록 완전 삭제)
    public void deleteLike(LikeDto likeDto) {
    	noticeMapper.deleteLike(likeDto);
    }
    
    // 좋아요 다시하기(로그가 남아 있을시 좋아요를 update형식으로 구현)
    public void reLike(LikeDto likeDto) {
    	noticeMapper.reLike(likeDto);
    }
    
    // 좋아요 수 카운트
    public Long likeCnt(Long postId) {
    	return noticeMapper.likeCnt(postId);
    } 
    
    // 카테고리 대분류 Board List 불러옴
    public List<BoardDto> selectBoardList() {
    	return noticeMapper.selectBoardList();
    }
}
