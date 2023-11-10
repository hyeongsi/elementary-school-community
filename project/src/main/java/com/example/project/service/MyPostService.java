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
public class MyPostService {

    final NoticeMapper noticeMapper;

    public MyPostService(final NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    
    public NoticePageDto selectSearchNoticePageById(final int displayUnit, final int curPage, String keyword, String searchType, int categoryId, String memberId, String write_date) {
        final Map<String, String> map = new HashMap<>();
        map.put("searchType", searchType);
        map.put("keyword", keyword);
        map.put("categoryId", Integer.toString(categoryId));
        map.put("memberId", memberId); // 사용자 ID를 검색 조건에 추가
        map.put("write_date", write_date); // 작성일 호출
        System.out.println("서비스단"+keyword+"서치타입"+searchType);
        System.out.println("map:"+map.get("keyword"));
        
        // 조회수
        final int totalCnt = noticeMapper.noticeTotalCnt(map);
        
        // 페이징
        final Page page = new Page(displayUnit, curPage, totalCnt);
        final MemberPageDto memberPageDto = new MemberPageDto(page.getStartNum(), page.getEndNum(), keyword, searchType, categoryId, memberId, write_date);
        
        System.out.println(memberPageDto.toString());
        // 페이징된 목록
        final List<NoticeDto> noticeDtoList = noticeMapper.selectSearchNoticePageById(memberPageDto);
        System.out.println(noticeMapper.selectSearchNoticePageById(memberPageDto));
        // 반환
        final NoticePageDto noticePageDto = new NoticePageDto(page, noticeDtoList);
        return noticePageDto;
    }
}
