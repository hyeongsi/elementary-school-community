package com.example.project.service;

import com.example.project.dto.BoardDto;
import org.springframework.stereotype.Service;
import com.example.project.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardService {

    final BoardMapper boardMapper;

    public BoardService(final BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    public List<BoardDto> selectBoardList() {
        return boardMapper.selectBoardList();
    }

    public int insertBoard(final BoardDto boardDto){
        return boardMapper.insertBoard(boardDto);
    }
}
