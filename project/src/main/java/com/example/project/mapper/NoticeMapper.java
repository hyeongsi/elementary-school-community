package com.example.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.PageDto;
import com.example.project.dto.SearchDto;
import com.example.project.dto.notice.NoticeDto;

@Mapper
@Repository
public interface NoticeMapper {

	public List<NoticeDto> selectNoticeList();
	public List<NoticeDto> selectNoticePage(PageDto pageDto);
	public int noticeTotalCnt();
	public int insertNotice(NoticeDto noticeDto);
	public int deleteNotice(Long postId);
	public NoticeDto selectByPostId(int postId);
	public int updateNotice(NoticeDto noticeDto);
}
