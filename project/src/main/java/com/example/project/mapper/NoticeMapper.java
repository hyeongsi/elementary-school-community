package com.example.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.project.dto.SearchDto;
import com.example.project.dto.notice.NoticeDto;

@Mapper
@Repository
public interface NoticeMapper {

	public List<NoticeDto> selectNoticeList();
	public int count(SearchDto searchDto);
	public Integer insertNotice(NoticeDto noticeDto);
	public int deleteNotice(Long postId);
	public NoticeDto selectByPostId(int postId);
	public Integer updateNotice(NoticeDto noticeDto);
}
