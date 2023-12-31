package com.example.project.mapper;

import com.example.project.dto.NoticeCategoryDto;
import com.example.project.dto.board.BoardDto;
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

    // 게시글  정보 리스트로 호출
    public List<NoticeDto> selectSearchNoticePageById(MemberPageDto memberPageDto);
    
    // 좋아요 누른 게시글 리스트로 호출
    public List<NoticeDto> selectSearchLikePostById(MemberPageDto memberPageDto);
    
    public int noticeTotalCnt(Map<String, String> map);

    public int noticeSearchTotalCnt();

    public int insertNotice(NoticeDto noticeDto);

    public int deleteNoticeList(List<NoticeDto> noticeDtoList);

    public int deleteNotice(Long postId);
    
    // 아이디값으로 게시글 정보 가져옴
    public NoticeDto selectByPostId(Long postId);

    public int updateNotice(NoticeDto noticeDto);

    public int updateViewCnt(Long postId);

    public List<NoticeCategoryDto> selectNoticeCategoryList();

    public List<NoticeCategoryDto> selectUserNoticeCategoryList(int boardId);

    public void addComment(CommentDto commnetDto);

    // 댓글 정보 가져옴
    public List<CommentDto> selectCommentList(Long postId);

    public List<CommentAllJoinDto> selectCommentListAll(PageDto pageDto);

    public int commentTotalCnt();

    public void deleteComment(Long commentId);
    
    public int deleteCheck(Long commentId);
    
    public void completeDelComment(Long commentId);

    public int deleteCommentList(List<CommentDto> commentDtoList);

    public void editComment(CommentDto commentDto);

    public int noticeNoSearchTotalCnt();

    public List<NoticeBoardCategoryMemberNameDto> selectNoticePageWithName(PageDto pageDto);

    public int noticeTotalCtnById(int categoryId);

    public List<NoticeBoardCategoryMemberNameDto> selectNoticeListById(FindNoticeByCategoryIdPageDto findNoticeByCategoryIdPageDto);

    public LikeDto likeCheck(LikeDto likeDto);

    public void addLike(LikeDto likeDto);

    public void cancelLike(LikeDto likeDto);
    
    public void deleteLike(LikeDto likeDto);

    public void reLike(LikeDto likeDto);

    public Long likeCnt(Long postId);
    
    public int mypageCnt(Map<String, String> map);
    
    public int mypageLikeCnt(Map<String, String> map);

    public List<BoardDto> selectBoardList();
}
