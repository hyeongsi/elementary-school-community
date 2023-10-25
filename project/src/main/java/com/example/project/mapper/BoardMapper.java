package com.example.project.mapper;

import com.example.project.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {

    public List<BoardDto> selectBoardList();
    public int insertBoard(BoardDto boardDto);
}
