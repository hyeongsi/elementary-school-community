package com.example.project.mapper;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {

    public List<BoardDto> selectBoardList();
    public int boardTotalCnt();
    public List<BoardDto> selectBoardPage(PageDto pageDto);
    public int insertBoard(BoardDto boardDto);
    public int deleteBoard(BoardDto boardDto);
    public int deleteBoardList(List<BoardDto> boardDtoList);
}