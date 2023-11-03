package com.example.project.dto.board;

import com.example.project.dto.page.Page;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("BoardPageDto")
public class BoardPageDto {

    private Page page;
    private List<BoardDto> boardDtoList;

    public BoardPageDto(Page page, List<BoardDto> boardDtoList) {
        this.page = page;
        this.boardDtoList = boardDtoList;
    }

    public Page getPage() {
        return page;
    }

    public List<BoardDto> getBoardDtoList() {
        return boardDtoList;
    }
}
