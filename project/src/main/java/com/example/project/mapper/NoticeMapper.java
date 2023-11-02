package com.example.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.LikeDto;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.page.PageDto;

@Mapper
@Repository
public interface NoticeMapper {

	public List<NoticeDto> selectNoticeList();
	//public List<NoticeDto> selectNoticePage(PageDto pageDto);
	public List<NoticeDto> selectSearchNoticePage(PageDto pageDto);
	public int noticeTotalCnt(Map<String, String> map);
	public int noticeSearchTotalCnt();
	public int insertNotice(NoticeDto noticeDto);
	public int deleteNotice(Long postId);
	public NoticeDto selectByPostId(Long postId);
	public int updateNotice(NoticeDto noticeDto);
	public int updateViewCnt(Long postId);
	public List<NoticeCategoryDto> selectNoticeCategoryList();
	public List<NoticeCategoryDto> selectUserNoticeCategoryList(int boardId);
	public void addComment(CommentDto commnetDto);
	public List<CommentDto> selectCommentList(Long postId);
	public void deleteComment(Long commentId);
	public void editComment(CommentDto commentDto);
	public LikeDto likeCheck(LikeDto likeDto);
	public void addLike(LikeDto likeDto);
	public void cancelLike(LikeDto likeDto);
	public void reLike(LikeDto likeDto);
	public Long likeCnt(Long postId);
}
