package com.example.project.repository;

import com.example.project.dto.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    final SqlSessionTemplate session;

    public BoardRepositoryImpl(final SqlSessionTemplate session) {
        this.session = session;
    }

    @Override
    public List<BoardDto> selectBoardList(){
        return session.selectList("BoardMapper.selectBoardList");
    }

    @Override
    public int insertBoard(final BoardDto boardDto) {
        return session.insert("BoardMapper.insertBoard", boardDto);
    }
}
