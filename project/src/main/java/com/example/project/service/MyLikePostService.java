package com.example.project.service;

import com.example.project.dto.notice.NoticeDto;
import com.example.project.dto.notice.NoticePageDto;
import com.example.project.dto.page.MemberPageDto;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;

import org.springframework.stereotype.Service;
import com.example.project.mapper.NoticeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyLikePostService {

    final NoticeMapper noticeMapper;

    public MyLikePostService(final NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    
    public NoticePageDto selectSearchLikePostById(final int displayUnit, final int curPage, String keyword, String searchtype, int categoryId, String memberId, String write_date) {
        final Map<String, String> map = new HashMap<>();
        map.put("searchType", searchtype);
        map.put("keyword", keyword);
        map.put("categoryId", Integer.toString(categoryId));
        map.put("memberId", memberId); // 사용자 ID를 검색 조건에 추가
        map.put("write_date", write_date); // 사용자 ID를 검색 조건에 추가
        
        // 조회수
        final int totalCnt = noticeMapper.mypageLikeCnt(map);
        
        // 페이징
        final Page page = new Page(displayUnit, curPage, totalCnt);
        final MemberPageDto memberPageDto = new MemberPageDto(page.getStartNum(), page.getEndNum(), keyword, searchtype, categoryId, memberId, write_date);
        
        // 페이징된 목록
        final List<NoticeDto> noticeDtoList = noticeMapper.selectSearchLikePostById(memberPageDto);
        
        // 반환
        final NoticePageDto noticePageDto = new NoticePageDto(page, noticeDtoList);
        return noticePageDto;
    }
}
