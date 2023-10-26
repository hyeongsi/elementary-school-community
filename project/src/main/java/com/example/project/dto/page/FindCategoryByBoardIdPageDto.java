package com.example.project.dto.page;

import org.apache.ibatis.type.Alias;

@Alias("FindCategoryByBoardIdPageDto")
public class FindCategoryByBoardIdPageDto {

    private final PageDto pageDto;
    private final int boardId;

    public FindCategoryByBoardIdPageDto(PageDto pageDto, int boardId) {
        this.pageDto = pageDto;
        this.boardId = boardId;
    }

    public PageDto getPageDto() {
        return pageDto;
    }

    public int getBoardId() {
        return boardId;
    }
}
