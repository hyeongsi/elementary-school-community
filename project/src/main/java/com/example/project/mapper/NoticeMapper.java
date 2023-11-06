package com.example.project.mapper;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.notice.*;
import com.example.project.dto.page.MemberPageDto;
import com.example.project.dto.page.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NoticeMapper {

	public List<NoticeDto> selectNoticeList();
	//public List<NoticeDto> selectNoticePage(PageDto pageDto);
	public List<NoticeDto> selectSearchNoticePage(PageDto pageDto);
	
	public List<NoticeDto> selectSearchNoticePageById(MemberPageDto memberPageDto);
	
	
	public int noticeTotalCnt(Map<String, String> map);
	public int noticeSearchTotalCnt();
	public int insertNotice(NoticeDto noticeDto);
	public int deleteNotice(Long postId);
	public int deleteNoticeList(List<NoticeDto> noticeDtoList);
	public NoticeDto selectByPostId(Long postId);
	public int updateNotice(NoticeDto noticeDto);
	public int updateViewCnt(Long postId);
	public List<NoticeCategoryDto> selectNoticeCategoryList();
	public List<NoticeCategoryDto> selectUserNoticeCategoryList(int boardId);
	public void addComment(CommentDto commnetDto);
	public List<CommentDto> selectCommentList(Long postId);
	public List<CommentAllJoinDto> selectCommentListAll(PageDto pageDto);
	public int commentTotalCnt();
	public void deleteComment(Long commentId);
	public int deleteCommentList(List<CommentDto> commentDtoList);
	public void editComment(CommentDto commentDto);
	public int noticeNoSearchTotalCnt();
	public List<NoticeBoardCategoryMemberNameDto> selectNoticePageWithName(PageDto pageDto);
	public int noticeTotalCtnById(int categoryId);
	public List<NoticeBoardCategoryMemberNameDto> selectNoticeListById(FindNoticeByCategoryIdPageDto findNoticeByCategoryIdPageDto);
	public LikeDto likeCheck(LikeDto likeDto);
	public void addLike(LikeDto likeDto);
	public void cancelLike(LikeDto likeDto);
	public void reLike(LikeDto likeDto);
	public Long likeCnt(Long postId);
}
