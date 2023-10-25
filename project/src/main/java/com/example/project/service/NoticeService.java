package com.example.project.service;

import com.example.project.dto.PageDto;
import com.example.project.dto.SearchDto;
import com.example.project.dto.Board.BoardDto;
import com.example.project.dto.Board.BoardPageDto;
import com.example.project.dto.Page.Page;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;

import org.springframework.stereotype.Service;
import com.example.project.mapper.NoticeMapper;

import java.util.List;

@Service
public class NoticeService {

    final NoticeMapper noticeMapper;

    public NoticeService(final NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    
    
    public NoticePageDto selectNoticePage(final int displayUnit, final int curPage) {
    	final int totalCnt = noticeMapper.noticeTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<NoticeDto> noticeDtoList = noticeMapper.selectNoticePage(pageDto);

        final NoticePageDto noticePageDto = new NoticePageDto(page, noticeDtoList);
        return noticePageDto;
    	
    }
    
    public List<NoticeDto> selectNoticieList() {
        return noticeMapper.selectNoticeList();
    }
    
    public int insertNotice(final NoticeDto noticeDto) {
    	return noticeMapper.insertNotice(noticeDto);
    }
    public int deleteNotice(Long postId) {
    	return noticeMapper.deleteNotice(postId);
    }
    
    public NoticeDto selectByPostId(int postId) {
    	return noticeMapper.selectByPostId(postId);
    }
    public int updateNotice(final NoticeDto noticeDto) {
    	return noticeMapper.updateNotice(noticeDto);
    }

}
