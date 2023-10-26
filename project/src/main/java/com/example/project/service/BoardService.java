package com.example.project.service;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.board.BoardPageDto;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;
import com.example.project.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    final BoardMapper boardMapper;

    public BoardService(final BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public BoardPageDto selectBoardPage(final int displayUnit, final int curPage) {

        final int totalCnt = boardMapper.boardTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<BoardDto> boardDtoList = boardMapper.selectBoardPage(pageDto);

        final BoardPageDto boardPageDto = new BoardPageDto(page, boardDtoList);
        return boardPageDto;
    }

    public int insertBoard(final BoardDto boardDto){
        return boardMapper.insertBoard(boardDto);
    }

    public int deleteBoard(final BoardDto boardDto){
        return boardMapper.deleteBoard(boardDto);
    }

    public int deleteBoardList(final List<BoardDto> boardDtoList){
        return boardMapper.deleteBoardList(boardDtoList);
    }
}
