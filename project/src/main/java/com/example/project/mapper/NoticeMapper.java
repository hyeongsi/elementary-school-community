package com.example.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.PageDto;
import com.example.project.dto.notice.NoticeDto;

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
	
}
