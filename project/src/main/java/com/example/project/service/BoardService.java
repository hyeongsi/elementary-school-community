package com.example.project.service;

import com.example.project.dto.BoardDto;
import com.example.project.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    final BoardRepository boardRepository;

    public BoardService(final BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> selectBoardList() {
        return boardRepository.selectBoardList();
    }

    public int insertBoard(final BoardDto boardDto){
        return boardRepository.insertBoard(boardDto);
    }
}
