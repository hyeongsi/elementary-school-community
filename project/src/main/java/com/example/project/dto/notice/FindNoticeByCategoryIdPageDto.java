package com.example.project.dto.notice;

import com.example.project.dto.page.PageDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@RequiredArgsConstructor
@Alias("FindNoticeByCategoryIdPageDto")
public class FindNoticeByCategoryIdPageDto {

    private final PageDto pageDto;
    private final int categoryId;

}
