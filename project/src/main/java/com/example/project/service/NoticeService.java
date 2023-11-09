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
    
    public List<NoticeDto> selectNoticieList() {
        return noticeMapper.selectNoticeList();
    }
    
    public int updateViewCnt(Long postId) {
    	
    	return noticeMapper.updateViewCnt(postId);
    }
    
    public int insertNotice(final NoticeDto noticeDto) {
    	return noticeMapper.insertNotice(noticeDto);
    }
    public int deleteNotice(Long postId) {
    	return noticeMapper.deleteNotice(postId);
    }
    
    // 아이디값으로 게시글 정보 가져옴
    public NoticeDto selectByPostId(Long postId) {
    	return noticeMapper.selectByPostId(postId);
    }
    
    public int updateNotice(final NoticeDto noticeDto) {
    	return noticeMapper.updateNotice(noticeDto);
    }

    public List<NoticeCategoryDto> selectNoticeCategoryList() {
    	return noticeMapper.selectNoticeCategoryList();
    }
    
    public List<NoticeCategoryDto> selectUserNoticeCategoryList(int boardId) {
    	return noticeMapper.selectUserNoticeCategoryList(boardId);
    }
    
    public void addComment(CommentDto commnetDto) {
    	noticeMapper.addComment(commnetDto);
    }
    
    // 댓글 정보 가져옴
    public List<CommentDto> selectCommentList(Long postId){
    	return noticeMapper.selectCommentList(postId);
    }
    
    public void deleteComment(Long commentId) {
    	noticeMapper.deleteComment(commentId);
    }
    public int deleteCheck(Long commentId) {
    	return noticeMapper.deleteCheck(commentId);
    }
    
    public void completeDelComment(Long commentId) {
    	noticeMapper.completeDelComment(commentId);
    }
    
    public void editComment(CommentDto commentDto) {
    	noticeMapper.editComment(commentDto);
    }
    
    public LikeDto likeCheck(LikeDto likeDto) {
    	return noticeMapper.likeCheck(likeDto);
    }
    
    public void addLike(LikeDto likeDto) {
    	noticeMapper.addLike(likeDto);
    }
    
    public void cancelLike(LikeDto likeDto) {
    	noticeMapper.cancelLike(likeDto);
    }
    
    public void deleteLike(LikeDto likeDto) {
    	noticeMapper.deleteLike(likeDto);
    }
    public void reLike(LikeDto likeDto) {
    	noticeMapper.reLike(likeDto);
    }
    
    public Long likeCnt(Long postId) {
    	return noticeMapper.likeCnt(postId);
    } 
    
    public List<BoardDto> selectBoardList() {
    	return noticeMapper.selectBoardList();
    }
}
