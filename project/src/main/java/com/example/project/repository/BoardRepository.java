package com.example.project.repository;

import com.example.project.dto.BoardDto;

import java.util.List;

public interface BoardRepository {

    public List<BoardDto> selectBoardList();
    public int insertBoard(BoardDto boardDto);
}
