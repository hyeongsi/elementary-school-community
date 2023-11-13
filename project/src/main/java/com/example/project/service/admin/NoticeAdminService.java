package com.example.project.service.admin;

import com.example.project.dto.notice.*;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;
import com.example.project.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeAdminService {

    private final NoticeMapper noticeMapper;

    public AdminNoticePageDto selectNoticePage(final int displayUnit, final int curPage) {

        final int totalCnt = noticeMapper.noticeNoSearchTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<NoticeBoardCategoryMemberNameDto> noticeDtoList = noticeMapper.selectNoticePageWithName(pageDto);

        final AdminNoticePageDto adminNoticePageDto = new AdminNoticePageDto(page, noticeDtoList);
        return adminNoticePageDto;
    }

    public int deleteNoticeList(List<NoticeDto> noticeDtoList){
        return noticeMapper.deleteNoticeList(noticeDtoList);
    }

    public AdminNoticePageDto selectNoticePageById(int categoryId, int displayUnit, int curPage) {
        final int totalCnt = noticeMapper.noticeTotalCtnById(categoryId);
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final FindNoticeByCategoryIdPageDto findNoticeByCategoryIdPageDto =
                new FindNoticeByCategoryIdPageDto(pageDto, categoryId);

        final List<NoticeBoardCategoryMemberNameDto> noticeBoardCategoryMemberNameDtoList =
                noticeMapper.selectNoticeListById(findNoticeByCategoryIdPageDto);

        final AdminNoticePageDto noticePageDto = new AdminNoticePageDto(page, noticeBoardCategoryMemberNameDtoList);
        return noticePageDto;
    }
}
