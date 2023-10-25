package com.example.project.dto.Board;

import com.example.project.dto.Page.Page;
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
