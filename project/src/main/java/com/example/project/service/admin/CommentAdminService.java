package com.example.project.service.admin;

import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.CommentPageDto;
import com.example.project.dto.notice.CommentAllJoinDto;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;
import com.example.project.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentAdminService {

    private final NoticeMapper noticeMapper;

    public CommentPageDto selectCommentPage(final int displayUnit, final int curPage) {

        final int totalCnt = noticeMapper.commentTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<CommentAllJoinDto> commentAllJoinDtoList = noticeMapper.selectCommentListAll(pageDto);

        final CommentPageDto commentPageDto = new CommentPageDto(page, commentAllJoinDtoList);
        return commentPageDto;
    }

    public int deleteCommentList(List<CommentDto> commentDtoList){
        return noticeMapper.deleteCommentList(commentDtoList);
    }

}
